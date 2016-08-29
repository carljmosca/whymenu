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
import com.whymenu.data.Store;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moscac
 */
public class StoreService {

    private FirebaseDatabase database;
    private DatabaseReference storesRef;
    private HashMap<String, Store> stores;

    public StoreService() {
        init();
    }

    private void init() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        storesRef = databaseReference.child("stores");
        storesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stores.put(dataSnapshot.getKey(), dataSnapshot.getValue(Store.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        storesRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onCancelled(DatabaseError de) {
            }

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                stores.put(dataSnapshot.getKey(), dataSnapshot.getValue(Store.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {
                stores.put(dataSnapshot.getKey(), dataSnapshot.getValue(Store.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot ds) {
                stores.remove(ds.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot ds, String previousChildKey) {
            }
        });
    }

    public void save(String key, Store store) {

        try {
            storesRef.push().setValue(store, (DatabaseError databaseError, DatabaseReference databaseReference1) -> {
                if (databaseError != null) {
                    Logger.getLogger(StoreService.class.getName()).log(Level.WARNING,
                            "Data could not be saved {0}", databaseError.getMessage());
                }
            });
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DatabaseReference getStoresRef() {
        return storesRef;
    }
}
