/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.whymenu.data.Location;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author moscac
 */
public class LocationServiceTest {

    private FirebaseService firebaseService;

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

        FirebaseService.getInstance();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class LocationService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        String key = "testlocation9999";
        Location location = new Location();
        location.setDescription("test description");
        location.setAddress1("123 Main Street");
        LocationService instance = new LocationService();
        instance.save(key, location);
        key = "totallyNewLocation2";
        instance.save(key, location);
    }

}
