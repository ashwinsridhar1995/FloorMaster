/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author ashwinsridhar
 */
public class Order {
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costperSqFoot;
    private BigDecimal laborCostperSqFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    private LocalDate orderDate;
    
    public Order(int orderNumber,String customerName,String state,BigDecimal taxRate,
            String productType,BigDecimal area,BigDecimal costperSqFoot,
            BigDecimal laborCostperSqFoot,BigDecimal materialCost,BigDecimal laborCost,
            BigDecimal tax,BigDecimal total) {
        this.orderNumber = orderNumber; this.customerName = customerName;
        this.state = state; this.taxRate = taxRate;
        this.productType = productType; this.area = area;
        this.costperSqFoot = costperSqFoot; this.laborCostperSqFoot = laborCostperSqFoot;
        this.materialCost = materialCost; this.laborCost = laborCost;
        this.tax = tax; this.total = total;
    }
    
    public Order(String customerName) {
        this.customerName = customerName;
    }
    
  /*  public int getorderNumber() {
        return orderNumber;
    }
    
    public void setorderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }*/
    
    public String getcustomerName() {
        return customerName;
    }
    
  /*  public void setcustomerName(String customerName) {
       this.customerName = customerName;
    } */
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public BigDecimal gettaxRate() {
        return taxRate;
    }
    
  /*  public String getproductType() {
        return productType;
    }
    
    public void setproductType(String productType) {
        this.productType = productType;
    }*/
    
    public BigDecimal getArea() {
        return area;
    }
    
    public void setArea(BigDecimal area) {
        this.area = area;
    }
    
  /*  public BigDecimal getcostperSqFoot() {
        return costperSqFoot;
    }
    
    public BigDecimal getlaborCostperSqFoot() {
        return laborCostperSqFoot;
    }
    
    public BigDecimal getmaterialCost() {
        return materialCost;
    }
    
    public BigDecimal getlaborCost() {
        return laborCost;
    } */

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostperSqFoot() {
        return costperSqFoot;
    }

    public void setCostperSqFoot(BigDecimal costperSqFoot) {
        this.costperSqFoot = costperSqFoot;
    }

    public BigDecimal getLaborCostperSqFoot() {
        return laborCostperSqFoot;
    }

    public void setLaborCostperSqFoot(BigDecimal laborCostperSqFoot) {
        this.laborCostperSqFoot = laborCostperSqFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
 /*   public BigDecimal setlaborCost() {
        return laborCost;
    }
    
    public BigDecimal gettax() {
        return tax;
    }
    
    public BigDecimal gettotal() {
        return total;
    }*/
}
