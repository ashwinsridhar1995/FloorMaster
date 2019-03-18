/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floorMaster.ui;

import Enum.Order;
import com.sg.floorMaster.dao.DaoPersistenceException;
import com.sg.floorMaster.service.InvalidFileException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ashwinsridhar
 */
public class View {
    private UserIO io;
    public View(UserIO io){
        this.io = io;
    }
    
    public void welcomeBanner() {
        io.print("Welcome to SWG Corp");
    }
    
    public String validProductType(List<String> ListofProducts,Boolean edit) 
            throws DaoPersistenceException {
        boolean valid = false;
        do {
           String productType = io.readString("Please enter your preferred product type:");
           if(edit) {
               if(productType.equals("")) {
                   return productType;
               }
           }
           for(String product: ListofProducts) {
               if(product.equals(productType)) {
                   return productType;
               }
           }
           io.print("Invalid Product Type");
        }while(valid==false);
        return null;
    }
    
    public String validState(List<String> ListofStates,Boolean edit) 
            throws DaoPersistenceException {
        boolean valid = false;
        do {
           String State = io.readString("Please enter your home state:");
           if(edit) {
               if(State.equals("")) {
                   return State;
               }
           }
           for(String state: ListofStates) {
               if(state.equals(State)) {
                   return State;
               }
           }
           io.print("Invalid state");
        }while(valid==false);
        return null;
    }
    
    public String validArea(Boolean edit) throws DaoPersistenceException {
        boolean valid = false;
        do {
           String area = io.readString("Please enter your preferred area:");
           if(edit) {
               if(area.equals("")) {
                   return area;
               }
           }
           try { 
                // checking valid integer using parseInt() method 
                new BigDecimal (area);
                return area;
               // System.out.println(area + " is a valid integer number"); 
           }  
           catch (NumberFormatException e)  { 
                System.out.println(area + " is not a valid integer number"); 
           }
        }while(valid==false);
        return null;
    }
    
    public Order getNewOrderInfo(List<String> ListofProducts,List<String> ListofStates) throws DaoPersistenceException {
	    String Name = io.readString("Please enter your Name:");
            String state = validState(ListofStates,false);
            String productType = validProductType(ListofProducts,false);
	    String Area = validArea(false);
	    Order currentOrder = new Order(Name);
	    currentOrder.setState(state);
	    currentOrder.setProductType(productType);
	    currentOrder.setArea(new BigDecimal (Area));
	    return currentOrder;
    }
    
    public Order editOrderInfo(List<String> ListofProducts,List<String> ListofStates, Order editInfo) throws DaoPersistenceException {
	    String Name = io.readString("Please enter your Name(" + editInfo.getCustomerName() + "):");
            if(Name.equals("")) {
                Name = editInfo.getCustomerName();
            }
	    io.print("Original state(" + editInfo.getState() + "):");
            String State = validState(ListofStates,true);
            if(State.equals("")) {
                State = editInfo.getState();
            }
           // validState(ListofStates);
	    io.print("Original product type(" + editInfo.getProductType() + "):");
            String Product = validProductType(ListofProducts,true);
            if(Product.equals("")) {
                Product = editInfo.getProductType();
            }
            //validProductType(ListofProducts);
	    io.print("Original area:(" + editInfo.getArea() + "):");
            String Area = validArea(true);
            if(Area.equals("")) {
                Area = editInfo.getArea().toString();
            }
	    Order currentOrder = new Order(Name);
            currentOrder.setOrderNumber(editInfo.getOrderNumber());
	    currentOrder.setState(State);
	    currentOrder.setProductType(Product);
	    currentOrder.setArea(new BigDecimal (Area));
	    return currentOrder;
    }
    
    public String getOrderDate() throws DaoPersistenceException {
        String date = io.readString("Please enter the date of the order that already exists:");
        //LocalDate dateofOrder = LocalDate.parse(date);
        return date;
    }
    
    public int getOrderNumber() throws DaoPersistenceException {
        int orderNumber = io.readInt("Please enter the number of the order that already exists:");
        return orderNumber;
    }
    
    public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
    }
    
    public void displayOrder(List<Order> order) {
        for(Order orderInfo: order){
            io.print("Order Number: " + orderInfo.getOrderNumber());
            io.print("Name: " + orderInfo.getcustomerName() + 
                    "; State: " + orderInfo.getState() + "; TaxRate: " + orderInfo.gettaxRate() + "; ProductType: " + 
                    orderInfo.getProductType() + "; Area: " + orderInfo.getArea() + "; CostperSqFt: " +
                    orderInfo.getCostperSqFoot() + "; LaborCostperSqFt: " + orderInfo.getLaborCostperSqFoot() + 
                    "; MaterialCost: " + orderInfo.getMaterialCost() + "; LaborCost: " + orderInfo.getLaborCost() + 
                    "; Tax: " + orderInfo.getTax() + "; TotalCost: " + orderInfo.getTotal());
        } 
    }
    
    public int printMenuAndGetSelection() {
	io.print("<<Flooring Program>>");
	io.print("1. Display Orders");
	io.print("2. Add an Order");
	io.print("3. Edit an Order");
	io.print("4. Remove an Order");
        io.print("5. Quit");

	return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    public void displayAddNewOrder() {
	    io.print("=== Add Order ===");
    }
        
    public void displayAddSuccessBanner() {
	    io.readString("Order successfully added.  Please hit enter to continue");
    }
    
    public void displayRemoveSuccessBanner() {
	    io.readString("Order successfully removed.  Please hit enter to continue");
    }
    
    public void displayExitBanner() {
            io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
            io.print("Unknown Command!!!");
    }

}
