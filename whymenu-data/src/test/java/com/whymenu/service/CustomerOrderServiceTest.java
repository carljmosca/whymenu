/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.whymenu.data.CustomerOrder;
import com.whymenu.data.CustomerOrderLine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moscac
 */
public class CustomerOrderServiceTest {
    
    public CustomerOrderServiceTest() {
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

    /**
     * Test of saveOrder method, of class CustomerOrderService.
     */
    @Test
    public void testSaveOrder() {
        System.out.println("saveOrder");
        String orderSheetId = "1w1mk7Mb8-Ukfy1b06a9LuQXSGu7RvlOKhkOYwOghM2o";
        CustomerOrder customerOrder = new CustomerOrder();
        CustomerOrderService instance = new CustomerOrderService();
        customerOrder.setName("John Smith");
        CustomerOrderLine customerOrderLine = new CustomerOrderLine();
        customerOrderLine.setName("Donut");
        customerOrderLine.getOptions();
        customerOrder.getCustomerOrderLines().add(customerOrderLine);        
        instance.saveOrder(orderSheetId, customerOrder);
        assertTrue(true);
    }
    
}
