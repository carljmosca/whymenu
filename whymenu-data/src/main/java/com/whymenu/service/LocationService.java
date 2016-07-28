/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.whymenu.data.Location;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moscac
 */
public class LocationService {

    private FirebaseDatabase database;
    private DatabaseReference locationsRef;

    public LocationService() {
        init();
    }

    private void init() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        locationsRef = databaseReference.child("locations");
    }

    public void save(String key, Location location) {

        try {            
            locationsRef.push().setValue(location, (DatabaseError databaseError, DatabaseReference databaseReference1) -> {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            });
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DatabaseReference getLocationsRef() {
        return locationsRef;
    }
    
}
