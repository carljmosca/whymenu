/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service.firebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whymenu.data.Location;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moscac
 */
public class LocationService {

    private FirebaseDatabase database;
    private DatabaseReference locationsRef;
    private final String storeName;
    private HashMap<String, Location> locations;

    public LocationService(String storeName) {
        this.storeName = storeName;
        init();
    }

    private void init() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        locationsRef = databaseReference.child(storeName + "/locations");
        locationsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                locations.put(dataSnapshot.getKey(), dataSnapshot.getValue(Location.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        locationsRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onCancelled(DatabaseError de) {
            }

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                locations.put(dataSnapshot.getKey(), dataSnapshot.getValue(Location.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {
                locations.put(dataSnapshot.getKey(), dataSnapshot.getValue(Location.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot ds) {
                locations.remove(ds.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot ds, String previousChildKey) {
            }
        });
    }

    public void save(String key, Location location) {

        try {
            locationsRef.push().setValue(location, (DatabaseError databaseError, DatabaseReference databaseReference1) -> {
                if (databaseError != null) {
                    Logger.getLogger(LocationService.class.getName()).log(Level.WARNING,
                            "Data could not be saved {0}", databaseError.getMessage());
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
