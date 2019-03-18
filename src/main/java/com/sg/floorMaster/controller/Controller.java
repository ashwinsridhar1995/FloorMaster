/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floorMaster.controller;

import Enum.Order;
import com.sg.floorMaster.dao.Dao;
import com.sg.floorMaster.dao.DaoException;
import com.sg.floorMaster.dao.DaoPersistenceException;
import com.sg.floorMaster.service.InvalidFileException;
import com.sg.floorMaster.service.ServiceLayer;
import com.sg.floorMaster.ui.UserIO;
import com.sg.floorMaster.ui.UserIOConsoleImpl;
import com.sg.floorMaster.ui.View;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashwinsridhar
 */
public class Controller {
    UserIO io = new UserIOConsoleImpl();
    View view;
    ServiceLayer service;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
   
   // private UserIO io;
    public Controller(ServiceLayer service,View view){
        this.view = view;
        this.service = service;
    }
    
    public void run() {
	    boolean keepGoing = true;
	    int menuSelection = 0;
	    try {
	        while (keepGoing) {

	            menuSelection = view.printMenuAndGetSelection();

	            switch (menuSelection) {
	                case 1:
                            LocalDate date = LocalDate.parse(io.readString("Please enter the date: "),formatter);
	                    findOrder(date);
	                    break;
	                case 2:
	                    addOrder();
	                    break;
	                case 3:
	                    editOrder();
	                    break;
	                case 4:
	                    removeOrder();
	                    break;
                        case 5:
                            keepGoing = false;
                            break;
	                default:
	                    view.displayUnknownCommandBanner();
	            }

	        }
	        view.displayExitBanner();
	    } catch (DaoPersistenceException e) {
	        view.displayErrorMessage(e.getMessage());
	    } catch (DaoException ex) {
                view.displayErrorMessage(ex.getMessage());
        }
	}
    
    private void findOrder(LocalDate date) throws DaoException, DaoPersistenceException {
	List<Order> orderInfo = service.getOrder(date);
        view.displayOrder(orderInfo);
    }
    
    private void addOrder() throws DaoException, DaoPersistenceException {
        List<String> ListofStates = service.getAllStates();
        List<String> ListofProductTypes = service.getAllProducts();
        view.displayAddNewOrder();
	Order newOrder = view.getNewOrderInfo(ListofProductTypes,ListofStates);
         
        
        Set<Entry<String, String>> entry = service.getAllProductCosts(newOrder.getProductType()).entrySet();
        Entry<String,String> e = entry.iterator().next();
        
        newOrder = service.calculations(entry, e, newOrder);
        
        io.print(newOrder.getCustomerName() + " " + newOrder.getState() + " " + 
                newOrder.getProductType() + " " + newOrder.getArea());
        
        boolean yn = true;
        
        while(yn) {
           String store = io.readString("Would you like this data stored(y/n)?");
           switch(store)
           {
              case "y":
                  if("Training".equals(service.getMode())) {
                    io.print("Sorry, but to save this data, you must be in production mode");
                    yn = false;
                    break;
                  }
                  service.addOrder(LocalDate.now(),newOrder,false);
                  view.displayAddSuccessBanner();
                  yn = false;
                  break;

              case "n":
                  view.displayRemoveSuccessBanner();
                  yn = false;
                  break;

              default:
                  System.out.println("Please enter again ");
                  yn = true;
           }
        }
    }
    
    private void editOrder() throws DaoPersistenceException {
      List<String> ListofStates = service.getAllStates();
      List<String> ListofProductTypes = service.getAllProducts();
      LocalDate dateofOrder = LocalDate.parse(view.getOrderDate(), formatter);
      
      int orderNumber = view.getOrderNumber();
      
      Order editInfo = service.editOrder(dateofOrder,orderNumber);
      service.deleteOrder(dateofOrder,orderNumber);
      Order newInfo = view.editOrderInfo(ListofProductTypes,ListofStates,editInfo);
      
      Set<Entry<String, String>> entry = service.getAllProductCosts(newInfo.getProductType()).entrySet();
      Entry<String,String> e = entry.iterator().next();
        
      newInfo = service.calculations(entry, e, newInfo);
      
      if("Training".equals(service.getMode())) {
        io.print("Sorry, but to save this data, you must be in production mode");
      }
      else {
        service.addOrder(dateofOrder,newInfo,true);
        io.print("Order successfully edited");
      }
    }
    
    private void removeOrder() throws DaoException, DaoPersistenceException {
      LocalDate dateofOrder = LocalDate.parse(view.getOrderDate(), formatter);

      int orderNumber = view.getOrderNumber();
      
      boolean yn = true;
        
        while(yn) {
           String store = io.readString("Would you like this data removed(y/n)?");
           switch(store)
           {
              case "y":
                  if("Training".equals(service.getMode())) {
                    io.print("Sorry, but to save this data, you must be in production mode");
                    yn = false;
                    break;
                  }
                  service.deleteOrder(dateofOrder,orderNumber);
                  view.displayRemoveSuccessBanner();
                  yn = false;
                  break;

              case "n":
                  yn = false;
                  break;

              default:
                  System.out.println("Please enter again ");
                  yn = true;
           }
        }
      
    }
}
