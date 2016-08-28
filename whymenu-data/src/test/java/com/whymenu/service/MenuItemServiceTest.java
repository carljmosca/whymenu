/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.whymenu.service.sheets.MenuItemService;
import com.whymenu.data.MenuItem;
import java.util.List;
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
public class MenuItemServiceTest {
    
    public MenuItemServiceTest() {
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
     * Test of loadMenuItems method, of class MenuItemService.
     */
    @Test
    public void testLoadMenuItems() {
        System.out.println("loadMenuItems");
        String locationName = "Test Location";
        MenuItemService instance = new MenuItemService();
        List<MenuItem> result = instance.loadMenuItems(locationName);
        assertTrue(result != null && !result.isEmpty());
    }
    
}
