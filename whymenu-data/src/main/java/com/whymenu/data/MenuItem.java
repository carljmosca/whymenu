/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 9200684626624598340L;
    
    private String itemNumber;
    private String name;
    private String description;
    private int order;
    private List<MenuItemAttribute> attributes;
    private BigDecimal price;
    private String taxRateCode;
    
    public MenuItem() {
        attributes = new ArrayList<>();
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuItemAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<MenuItemAttribute> attributes) {
        this.attributes = attributes;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTaxRateCode() {
        return taxRateCode;
    }

    public void setTaxRateCode(String taxRateCode) {
        this.taxRateCode = taxRateCode;
    }
    
}
