/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floorMaster.dao;

import Enum.Order;
import com.sg.floorMaster.service.InvalidFileException;
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
public interface Dao {
    public List<Order> getOrder(LocalDate date) throws DaoPersistenceException;
    public BigDecimal findCost(String productType) throws DaoPersistenceException;
    public BigDecimal findTax(String state) throws DaoPersistenceException;
    public Order addOrder(LocalDate date, Order order,Boolean edit) throws DaoPersistenceException;
    public ArrayList<String> getAllProducts() throws DaoPersistenceException;
    public ArrayList<String> getAllStates() throws DaoPersistenceException;
    public int getOrderNumber() throws DaoPersistenceException;
    public Map<String, String> getAllProductCosts(String product) throws DaoPersistenceException;
    public BigDecimal getallStateTaxRates(String state) throws DaoPersistenceException;
    public void deleteOrder(LocalDate fileName,int orderNumber) throws DaoPersistenceException;
    public Order editOrder(LocalDate dateofOrder,int orderNumber) throws DaoPersistenceException;
    public String getMode() throws DaoPersistenceException;
}
