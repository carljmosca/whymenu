/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author moscac
 */
public class CustomerOrderTest {

    public CustomerOrderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSerializeCustomerOrder() {
        boolean processed = false;
        CustomerOrder instance = new CustomerOrder();
        try {
        String fileName = "./CustomerOrder.txt";
        System.out.println("setCustomerOrderLines");
        ArrayList<CustomerOrderLine> customerOrderLines = null;
        instance.setName("A name");
        instance.setCustomerOrderLines(customerOrderLines);
        CustomerOrderLine customerOrderLine = new CustomerOrderLine();
        customerOrderLine.setName("customer order line test");
        MenuItemAttributeOption menuItemAttributeOption = new MenuItemAttributeOption();
        menuItemAttributeOption.setDescription("menu item attribute option test");
        customerOrderLine.getOptions().add(menuItemAttributeOption);
        instance.getCustomerOrderLines().add(customerOrderLine);
        try (FileOutputStream fileOut = new FileOutputStream(fileName); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(instance);
        }

        try (FileInputStream fileIn = new FileInputStream(fileName); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            instance = (CustomerOrder) in.readObject();
            System.out.println(instance.getName());
            for (CustomerOrderLine c : instance.getCustomerOrderLines()) {
                System.out.println(c.getDescription());
                c.getOptions().stream().forEach((o) -> {
                    System.out.println(o.getDescription());
                });
            }
            processed = true;
        }
        } catch (IOException | ClassNotFoundException e) {
            
        }
        assertTrue(processed);
    }

}
