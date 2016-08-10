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
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = -5110862787659609068L;

    private String name;
    private String email;
    private ArrayList<CustomerOrderLine> customerOrderLines;

    public CustomerOrder() {
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
        if (customerOrderLines == null) {
            customerOrderLines = new ArrayList<>();
        }
        return customerOrderLines;
    }

}
