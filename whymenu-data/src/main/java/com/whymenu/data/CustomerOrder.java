/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class CustomerOrder {
    
    private String name;
    private String email;
    private final List<CustomerOrderLine> customerOrderLines;
    
    public CustomerOrder() {
        customerOrderLines = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CustomerOrderLine> getCustomerOrderLines() {
        return customerOrderLines;
    }
    
}
