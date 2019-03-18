/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floorMaster.service;

import Enum.Order;
import com.sg.floorMaster.dao.Dao;
import com.sg.floorMaster.dao.DaoPersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ashwinsridhar
 */
public class DaoTest {
    private Dao dao;
    
    public DaoTest() {
    // wire the Service Layer with stub implementations of the Dao and
    // Audit Dao
    // ClassRosterDao dao = new ClassRosterDaoStubImpl();
    // ClassRosterAuditDao auditDao = 
    //           new ClassRosterAuditDaoStubImpl();
    //
    // service = new ClassRosterServiceLayerImpl(dao, auditDao);
 
        ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = 
            ctx.getBean("dao", Dao.class);
    }

    public void testgetOrder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse("01012000",formatter);
        try {
            assertEquals(1, dao.getOrder(date).size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testfindCost() {
        BigDecimal cost = null;
        BigDecimal compare = new BigDecimal(2.75);
        try {
            cost = dao.findCost("Wood");
            assertEquals(cost,compare);
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetCost() {
        try {
            dao.findCost("Wood");
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testfindTax() {
        BigDecimal cost = null;
        BigDecimal compare = new BigDecimal(5.15);
        try {
            cost = dao.findCost("Wood");
            assertEquals(cost,compare);
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetTax() {
       try {
            dao.findTax("OH");
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        } 
    }
    
    public void testaddOrder() {
        Order order = new Order("0002");
        order.setState("OH");
        order.setProductType("Wood");
        order.setArea(new BigDecimal (25));
        try {
            dao.addOrder(LocalDate.now(),order,false);
            assertNotNull(order);
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetAllProducts() {    
        try {
            assertEquals(1, dao.getAllProducts().size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetAllStates() {
        try {
            assertEquals(1, dao.getAllStates().size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetOrderNumber() {
        try {
            assertNotNull(dao.getOrderNumber());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetAllProductCosts() {
        try {
            assertNotNull(dao.getAllProductCosts("Wood").size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetallStateTaxRates() {
       try {
            assertNotNull(dao.getAllProductCosts("Wood").size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        } 
    }
    
    public void testdeleteOrder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate dateofOrder = LocalDate.parse("01012000",formatter);
        
        Order order1 = new Order("0002");
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(new BigDecimal (25));

        
        try {
            dao.addOrder(dateofOrder, order1, false);
            dao.deleteOrder(dateofOrder, dao.getOrderNumber());
            assertEquals(1,dao.getOrder(dateofOrder).size());
            assertNull(order1);
         
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testeditOrder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate dateofOrder = LocalDate.parse("01012000",formatter);
        
        Order order1 = new Order("0002");
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(new BigDecimal (25));
        
        try {
            dao.addOrder(dateofOrder, order1, false);
            Order order2 = dao.editOrder(order1.getOrderDate(), dao.getOrderNumber());
            assertNotEquals(order1,order2);
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        } 
    }
    
    public void testgetMode() {
        try {
            assertNotNull(dao.getMode());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
}
