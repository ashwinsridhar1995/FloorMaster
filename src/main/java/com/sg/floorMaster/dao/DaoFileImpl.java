
package com.sg.floorMaster.dao;

import Enum.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ashwinsridhar
 */
public class DaoFileImpl implements Dao {
    private int orderNumber = 0;
    private static Scanner scan;
    List<Order> order = new ArrayList<Order>();
    List<String> productTypes = new ArrayList<>();
    Map<String, String> tax = new HashMap<String,String>();
    Map<String,Map<String,String>> cost = new HashMap<String,Map<String,String>>();
    Map<String,String> costType = new HashMap<String,String>();
    
    public static final String ROSTER_FILE = "output.txt";
    public static final String DELIMITER = ",";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    
    @Override
    public List<Order> getOrder(LocalDate date) throws DaoPersistenceException {
        loadInventory(date);
        return order;
    }
    
    public Order editOrder(LocalDate dateofOrder,int orderNumber) throws DaoPersistenceException {
	loadInventory(dateofOrder);
        for(Order orders: order) {
            if(orders.getOrderNumber() == orderNumber) {
               return orders;
            }
        }
        return null;
    }

    public void deleteOrder(LocalDate fileName,int orderNumber) throws DaoPersistenceException {
        loadInventory(fileName);
        for(Order orders: order) {
            if(orders.getOrderNumber() == orderNumber) {
                order.remove(orders);
                break;
            }
        }
        writeOutput(fileName);
    }
    
    private void getTax() throws DaoPersistenceException {
        Scanner scanner;
        try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader("Taxes.txt")));
	    } catch (FileNotFoundException e) {
	        throw new DaoPersistenceException(
	                "-_- Could not load roster data into memory.", e);
	}
        String currentLine;
	  
	String[] currentTokens;
        
        while (scanner.hasNextLine()) {
	      currentLine = scanner.nextLine();
	       
	      currentTokens = currentLine.split(DELIMITER);
              
              tax.put(currentTokens[0],currentTokens[1]);
              
        }
        
        scanner.close();
    }
    
    public ArrayList<String> getAllProducts() throws DaoPersistenceException {
        getCost();
	ArrayList<String>productsInList = new ArrayList<>();
	productsInList.addAll(cost.keySet());
	return productsInList;
    }
    
    public Map<String, String> getAllProductCosts(String product) throws DaoPersistenceException {
        getCost();
	return cost.get(product);
    }
    
    public BigDecimal getallStateTaxRates(String state) throws DaoPersistenceException {
        getTax();
        return new BigDecimal (tax.get(state));
    }
    
    public ArrayList<String> getAllStates() throws DaoPersistenceException {
        getTax();
        ArrayList<String>statesInList = new ArrayList<>();
        statesInList.addAll(tax.keySet());
        return statesInList;
    }
    
    private void getCost() throws DaoPersistenceException {
        Scanner scanner;
        try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader("Products.txt")));
	    } catch (FileNotFoundException e) {
	        throw new DaoPersistenceException(
	                "-_- Could not load roster data into memory.", e);
	}
        String currentLine;
	  
	String[] currentTokens;
        
        while (scanner.hasNextLine()) {
	      currentLine = scanner.nextLine();
	       
	      currentTokens = currentLine.split(DELIMITER);
              
              costType.put(currentTokens[1],currentTokens[2]);
              cost.put(currentTokens[0],costType);
              
        }
        
        scanner.close();
    }
    
    
    public BigDecimal findTax(String state) throws DaoPersistenceException {
        getTax();
        return new BigDecimal(tax.get(state));
    }
    
    public BigDecimal findCost(String productType) throws DaoPersistenceException {
        getCost();
        return new BigDecimal((BigInteger) cost.get(productType));
    }
    
    public String getMode() throws DaoPersistenceException {
        Scanner scan;
        try {
	        scan = new Scanner(
	                new BufferedReader(
	                        new FileReader("mode.txt")));
	} catch (FileNotFoundException e) {
	        throw new DaoPersistenceException(
	                "-_- Could not load data into memory.", e);
	  }
        String mode = scan.nextLine();
        scan.close();
        return mode;
    }
    
    private void loadInventory(LocalDate dateofOrder) throws DaoPersistenceException {
	    Scanner scanner;
	    order.clear();
            
	    try {
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader("Order_" + dateofOrder.format(formatter) + ".txt")));
	    } catch (FileNotFoundException e) {
	        throw new DaoPersistenceException(
	                "-_- Could not load data into memory.", e);
	    }
	    
	    String currentLine;
	  
	    String[] currentTokens;
	    
	    while (scanner.hasNextLine()) {
	       currentLine = scanner.nextLine();
	       
	       currentTokens = currentLine.split(DELIMITER);
               
	   
               int orderNumber = Integer.parseInt(currentTokens[0]);
               String customerName = currentTokens[1];
               String state = currentTokens[2];
               BigDecimal taxRate = new BigDecimal (currentTokens[3]);
               String productType = currentTokens[4];
               BigDecimal area = new BigDecimal (currentTokens[5]);
               BigDecimal costperSqFoot = new BigDecimal (currentTokens[6]);
               BigDecimal laborCostperSqFoot = new BigDecimal (currentTokens[7]);
               BigDecimal materialCost = new BigDecimal (currentTokens[8]);
               BigDecimal laborCost = new BigDecimal (currentTokens[9]);
               BigDecimal tax = new BigDecimal (currentTokens[10]);
               BigDecimal total = new BigDecimal (currentTokens[11]);
               
               Order orderInfo = new Order(orderNumber,customerName,state,taxRate,
            productType,area,costperSqFoot,laborCostperSqFoot,materialCost,laborCost,
            tax,total);
                
                order.add(orderInfo); 
	        
	       
	    }
	    // close scanner
	    scanner.close();
	}
    
    public Order addOrder(LocalDate date, Order order,Boolean edit) throws DaoPersistenceException {
           if(!edit) {
              order.setOrderNumber(getOrderNumber());
           }
           try {
            loadInventory(date);
           } catch(DaoPersistenceException e) {
               //File does not exist
           }
           this.order.add(order);
	   writeOutput(date);
	   return order;
    }
    
    
    
    private void writeOutput(LocalDate date) throws DaoPersistenceException {
            List<Order> newOrder = order;
	    PrintWriter out;
	    
	    try {
	        out = new PrintWriter(new FileWriter("Order_" + date.format(formatter) + ".txt"));
	    } catch (IOException e) {
	        throw new DaoPersistenceException(
	                "Could not save data.", e);
	    }

	    for (Order orders : newOrder) {
	        out.println(orders.getOrderNumber() + DELIMITER + orders.getcustomerName() + 
                    DELIMITER + orders.getState() + DELIMITER + orders.gettaxRate() + DELIMITER + 
                    orders.getProductType() + DELIMITER + orders.getArea() + DELIMITER +
                    orders.getCostperSqFoot() + DELIMITER + orders.getLaborCostperSqFoot() + DELIMITER +
                    orders.getMaterialCost() + DELIMITER + orders.getLaborCost() + DELIMITER + 
                    orders.getTax() + DELIMITER + orders.getTotal());
                
	        out.flush();
                
                    Integer maxOrderNumber = orderNumber;
                    maxOrderNumber++;
                    orderNumber = maxOrderNumber++;
                
	    }
	    // Clean up
	    out.close();
	}
    
    public int getOrderNumber() throws DaoPersistenceException {
        Scanner scanner;
        PrintWriter out;
	    
	try {
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader("ordernumber.txt")));
	    } catch (FileNotFoundException e) {
	        throw new DaoPersistenceException(
	                "-_- Could not load roster data into memory.", e);
	    }
        String currentLine = scanner.nextLine();
        int maxorderNumber = Integer.parseInt(currentLine);
        
	    
        try {
            out = new PrintWriter(new FileWriter("ordernumber.txt"));
        } catch (IOException ex) {
            throw new DaoPersistenceException(
	                "-_- Could not load data into memory.", ex);
        }
        out.println(++maxorderNumber);
        out.flush();
        out.close();
        
        return maxorderNumber;
    }
}