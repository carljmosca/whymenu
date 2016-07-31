/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class MenuItem implements Serializable {
    
    private String name;
    private String description;
    private int index;
    private List<MenuItemAttribute> attributes;
    
    public MenuItem() {
        attributes = new ArrayList<>();
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
}
