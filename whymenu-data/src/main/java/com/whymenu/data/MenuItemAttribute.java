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
public class MenuItemAttribute implements Serializable {
    
    private int index;
    private List<MenuItemAttributeDetail> details;
    private boolean multiSelect;
    
    public MenuItemAttribute() {
        details = new ArrayList<>();
    }
    
    public MenuItemAttribute(int index, boolean multiSelect) {
        this.index = index;
        this.multiSelect = multiSelect;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<MenuItemAttributeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<MenuItemAttributeDetail> details) {
        this.details = details;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }
    
}
