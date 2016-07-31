/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.whymenu.data.Location;
import com.whymenu.service.FirebaseService;
import com.whymenu.service.LocationService;
import java.util.HashMap;

/**
 *
 * @author moscac
 */
@SpringUI
public class MainUI extends UI {
    
    private LocationService locationService;
    private HashMap<String, Button> buttons;
    private HashMap<String, Location> locations;
    private VerticalLayout mainLayout;
    
    public MainUI() {
        
    }
    
    @Override
    protected void init(VaadinRequest request) {
        FirebaseService.getInstance();
        buttons = new HashMap<>();
        locations = new HashMap<>();
        locationService = new LocationService();
        addComponents();
        mainLayout = new VerticalLayout();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.addComponent(new Label("test"));
        setContent(mainLayout);
        setPollInterval(1000);
        new Thread(new LocationFactory()).start();
    }
    
    private void addComponents() {
        locationService.getLocationsRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                locations.put(dataSnapshot.getKey(), dataSnapshot.getValue(Location.class));
            }
            
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        locationService.getLocationsRef().addChildEventListener(new ChildEventListener() {
            
            @Override
            public void onCancelled(DatabaseError de) {
            }
            
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                locations.put(dataSnapshot.getKey(), dataSnapshot.getValue(Location.class));
            }
            
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {
                //removeLocation(previousChildKey);
                locations.put(dataSnapshot.getKey(), dataSnapshot.getValue(Location.class));
            }
            
            @Override
            public void onChildRemoved(DataSnapshot ds) {
                //removeLocation(ds.getKey());
            }
            
            @Override
            public void onChildMoved(DataSnapshot ds, String previousChildKey) {
                System.out.println(previousChildKey);
            }
        });
    }
    
    class LocationFactory implements Runnable {
        
        @Override
        public void run() {

            // Wrap UI updates in access to properly deal with locking
            access(() -> {
                updateLocations();
            });
        }
    }
    
    private void updateLocations() {
        locations.forEach((key, value) -> {
            Location location = value;
            if (!buttons.containsKey(key)) {
                Button button = new Button(location != null ? location.getDescription() : key);
                button.addClickListener((Button.ClickEvent event) -> {
                    System.out.println("test");
                });
                mainLayout.addComponent(button);
                buttons.put(key, button);                
            } else {
                Button button = buttons.get(key);
                if (!button.getCaption().equals(location.getDescription())) {
                    button.setCaption(location.getDescription());
                }
            }
        });
        buttons.forEach((key, value) -> {            
            if (!locations.containsKey(key)) {
                mainLayout.removeComponent(value);
                value = null;
            }
        });
    }
    
    private void removeLocation(String key) {
        
    }
    
}
