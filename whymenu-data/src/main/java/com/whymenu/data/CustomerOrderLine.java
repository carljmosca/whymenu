/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.data;

import com.whymenu.util.Utility;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class CustomerOrderLine {

    private int quantity;
    private String itemNumber;
    private String name;
    private BigDecimal price;
    private String taxRateCode;
    private final List<MenuItemAttributeOption> options;

    public CustomerOrderLine() {
        options = new ArrayList<>();
        quantity = 1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public List<MenuItemAttributeOption> getOptions() {
        return options;
    }

    public String getDescription() {
        StringBuilder orderItemDescription = new StringBuilder();
        orderItemDescription.append(name).append(Utility.CHARACTER_SPACE);
        for (MenuItemAttributeOption option : options) {
            orderItemDescription.append(option.getDescription()).append(Utility.CHARACTER_SPACE);
        }
        if (price != null) {
            orderItemDescription.append(quantity).append(Utility.CHARACTER_SPACE).append(Utility.CHARACTER_AMPERSAND).append(Utility.CHARACTER_SPACE).append(NumberFormat.getCurrencyInstance().format(price));
        }
        return orderItemDescription.toString().trim();
    }

}
