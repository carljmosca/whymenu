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

    private static final long serialVersionUID = 3160595632603953907L;
    private int order;
    private String description;
    private List<MenuItemAttributeOption> details;
    private boolean multiSelect;

    public MenuItemAttribute() {
        details = new ArrayList<>();
    }

    public MenuItemAttribute(int index, boolean multiSelect) {
        this.order = index;
        this.multiSelect = multiSelect;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuItemAttributeOption> getDetails() {
        return details;
    }

    public void setDetails(List<MenuItemAttributeOption> details) {
        this.details = details;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

}
