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
    
    private String description;
    private List<MenuItemAttribute> attributes;
    
    public MenuItem() {
        attributes = new ArrayList<>();
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
    
}
