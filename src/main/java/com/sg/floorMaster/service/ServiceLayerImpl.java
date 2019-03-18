/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floorMaster.service;

import Enum.Order;
import com.sg.floorMaster.dao.Dao;
import com.sg.floorMaster.dao.DaoException;
import com.sg.floorMaster.dao.DaoPersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author ashwinsridhar
 */
public class ServiceLayerImpl implements ServiceLayer {
    Dao dao;
    
   public ServiceLayerImpl(Dao dao) {
        this.dao = dao;
   }
    
   public List<Order> getOrder(LocalDate date) throws DaoPersistenceException {
        return dao.getOrder(date);
   }
   
   public BigDecimal getTotal(BigDecimal LaborCost, BigDecimal MaterialCost,BigDecimal Tax) throws DaoPersistenceException {
        return LaborCost.add(MaterialCost).add(Tax);
   }
   
   public Order addOrder(LocalDate date, Order order,Boolean edit) throws DaoPersistenceException {
       return dao.addOrder(date,order,edit);
   }
   
   public ArrayList<String> getAllProducts() throws DaoPersistenceException {
       return dao.getAllProducts();
   }
   
   public ArrayList<String> getAllStates() throws DaoPersistenceException {
       return dao.getAllStates();
   }
   
   public int getOrderNumber() throws DaoPersistenceException {
       return dao.getOrderNumber();
   }
   
   public Map<String, String> getAllProductCosts(String product) throws DaoPersistenceException {
       return dao.getAllProductCosts(product);
   }
   
   public BigDecimal getallStateTaxRates(String state) throws DaoPersistenceException {
       return dao.getallStateTaxRates(state);
   }
   
   public void deleteOrder(LocalDate fileName,int orderNumber) throws DaoPersistenceException {
       dao.deleteOrder(fileName, orderNumber);
   }
   
   public Order editOrder(LocalDate dateofOrder,int orderNumber) throws DaoPersistenceException {
       return dao.editOrder(dateofOrder,orderNumber);
   }
   
   public String getMode() throws DaoPersistenceException {
       return dao.getMode();
   }
   
   public Order calculations(Set<Entry<String, String>> entry,Entry<String,String> e,Order newOrder) throws DaoPersistenceException {
       newOrder.setLaborCostperSqFoot(new BigDecimal (e.getValue()));
       newOrder.setLaborCost(newOrder.getLaborCostperSqFoot().multiply(newOrder.getArea()));
        
       newOrder.setCostperSqFoot(new BigDecimal (e.getKey()));
       newOrder.setMaterialCost(newOrder.getCostperSqFoot().multiply(newOrder.getArea()));
        
       newOrder.setTaxRate(getallStateTaxRates(newOrder.getState()));
       newOrder.setTax(newOrder.getTaxRate().multiply(newOrder.getArea()));
        
       newOrder.setTotal(getTotal(newOrder.getLaborCost(), newOrder.getMaterialCost(), newOrder.getTax()));
       
       return newOrder;
   }
}
