/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.data;

/**
 *
 * @author moscac
 */
public class MenuItemAttributeOption {
  
    private int order;
    private String description;
    private boolean available;
    
    public MenuItemAttributeOption() {
        
    }

    public MenuItemAttributeOption(int index, String description, boolean available) {
        this.order = index;
        this.description = description;
        this.available = available;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
}
