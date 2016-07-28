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
public class MenuItemAttributeDetail {
  
    private int index;
    private String description;
    private boolean available;
    
    public MenuItemAttributeDetail() {
        
    }

    public MenuItemAttributeDetail(int index, String description, boolean available) {
        this.index = index;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
}
