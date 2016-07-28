/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whymenu.data.MenuItem;

/**
 *
 * @author moscac
 */
public class MenuItems {
    
    @JsonProperty("menuitems")
    private MenuItem[] menuItems;
    
    public MenuItems() {
        
    }

}
