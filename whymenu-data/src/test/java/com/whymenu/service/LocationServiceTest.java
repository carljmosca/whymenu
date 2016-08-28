/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.whymenu.service.sheets.LocationService;
import com.whymenu.data.Location;
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
public class LocationServiceTest {
    
    public LocationServiceTest() {
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
     * Test of loadLocations method, of class LocationService.
     */
    @Test
    public void testLoadLocations() {
        System.out.println("loadLocations");
        LocationService instance = new LocationService();
        List<Location> result = instance.loadLocations();
        assertTrue(result != null && !result.isEmpty());
    }
    
}
