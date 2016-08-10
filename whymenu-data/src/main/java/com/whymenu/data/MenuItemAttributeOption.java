/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * @author moscac
 */
public class MenuItemAttributeOption implements Serializable {

    private int order;
    private UUID uuid;
    private BigDecimal price;
    private String description;
    private boolean available;
    private String attributeDescription;

    public MenuItemAttributeOption() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public MenuItemAttributeOption(int index, String description, boolean available, BigDecimal price) {
        this.order = index;
        this.description = description;
        this.available = available;
        this.price = price;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
    }    

    @Override
    public String toString() {
        return "MenuItemAttributeOption{" + "order=" + order + ", uuid=" + uuid + ", price=" + price + ", description=" + description + ", available=" + available + ", attributeDescription=" + attributeDescription + '}';
    }
}
