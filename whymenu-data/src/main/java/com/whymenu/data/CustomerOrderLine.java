/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.data;

import com.whymenu.util.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author moscac
 */
public class CustomerOrderLine implements Serializable {

    private static final long serialVersionUID = 752093268978563281L;

    private UUID uuid;
    private int quantity;
    private String itemNumber;
    private String name;
    private BigDecimal price;
    private String taxRateCode;
    private ArrayList<MenuItemAttributeOption> options;

    public CustomerOrderLine() {
        quantity = 1;
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
        if (options == null) {
            options = new ArrayList<>();
        }
        return options;
    }

    public void setOptions(ArrayList<MenuItemAttributeOption> options) {
        this.options = options;
    }

    public String getDescription() {
        StringBuilder orderItemDescription = new StringBuilder();
        orderItemDescription.append(name).append(Utility.CHARACTER_SPACE);
        if (options != null) {
            for (MenuItemAttributeOption option : options) {
                orderItemDescription.append(option.getDescription()).append(Utility.CHARACTER_SPACE);
            }
        }
        if (price != null) {
            orderItemDescription.append(quantity).append(Utility.CHARACTER_SPACE).append(Utility.CHARACTER_AMPERSAND).append(Utility.CHARACTER_SPACE).append(NumberFormat.getCurrencyInstance().format(price));
        }
        return orderItemDescription.toString().trim();
    }

}
