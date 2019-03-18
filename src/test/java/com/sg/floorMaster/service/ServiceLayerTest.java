/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floorMaster.service;


import Enum.Order;
import com.sg.floorMaster.dao.DaoPersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author ashwinsridhar
 */
public class ServiceLayerTest {
    private ServiceLayer service;
    
    public ServiceLayerTest() {
    // wire the Service Layer with stub implementations of the Dao and
    // Audit Dao
    // ClassRosterDao dao = new ClassRosterDaoStubImpl();
    // ClassRosterAuditDao auditDao = 
    //           new ClassRosterAuditDaoStubImpl();
    //
    // service = new ClassRosterServiceLayerImpl(dao, auditDao);
 
        ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
        service = 
            ctx.getBean("serviceLayer", ServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaddOrder() throws Exception {
        Order order = new Order("John Doe");
        order.setState("OH");
        order.setProductType("Wood");
        order.setArea(new BigDecimal (10));
        
        try{
            service.addOrder(LocalDate.now(),order,false);
            assertNotNull(order);
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    @Test
    public void testeditOrder() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate dateofOrder = LocalDate.parse("01012000",formatter);
        
        Order order1 = new Order("0002");
        order1.setState("OH");
        order1.setProductType("Wood");
        order1.setArea(new BigDecimal (25));
        
        try {
            service.addOrder(dateofOrder, order1, false);
            Order order2 = service.editOrder(order1.getOrderDate(), service.getOrderNumber());
            assertNotEquals(order1,order2);
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }

    @Test
    public void testgetOrder() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse("01012000",formatter);
        try {
            assertEquals(1, service.getOrder(date).size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetCalculations() throws Exception {
       Order onlyOrder = new Order("0002");
       onlyOrder.setState("OH");
       onlyOrder.setProductType("Wood");
       onlyOrder.setArea(new BigDecimal (25));
        
       Set<Map.Entry<String, String>> entry = service.getAllProductCosts(onlyOrder.getProductType()).entrySet();
       Map.Entry<String,String> e = entry.iterator().next();
       
       try {
            onlyOrder = service.calculations(entry, e, onlyOrder);
            assertEquals(12, onlyOrder);
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
       
    }
    
    public void testservicegetOrder() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse("01012000",formatter);
        try {
            assertEquals(1, service.getOrder(date).size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }

    public void testgetAllProducts() throws Exception {    
        try {
            assertEquals(1, service.getAllProducts().size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetAllStates() throws Exception {
        try {
            assertEquals(1, service.getAllStates().size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetOrderNumber() throws Exception {
        try {
            assertNotNull(service.getOrderNumber());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetAllProductCosts() throws Exception {
        try {
            assertNotNull(service.getAllProductCosts("Wood").size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
    
    public void testgetallStateTaxRates() throws Exception {
       try {
            assertNotNull(service.getAllProductCosts("Wood").size());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        } 
    }
    
    public void testgetMode() {
        try {
            assertNotNull(service.getMode());
        } catch (DaoPersistenceException ex) {
            Assert.fail();
        }
    }
}
