/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.whymenu.ui.view;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.whymenu.data.Location;
import com.whymenu.service.LocationService;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author moscac
 */
public class LocationView extends NavigationView {

    private VerticalComponentGroup content;
    private LocationService locationService;
    private Map<String, NavigationButton> buttons;
    private Map<String, Location> locations;

    public LocationView() {
        init();
    }

    private void init() {
        locationService = new LocationService();
        buttons = new HashMap<>();
        locations = new HashMap<>();
        addComponents();        
    }

    private void addComponents() {
        setCaption("Locations");

        content = new VerticalComponentGroup();

        setContent(content);
        locationService.getLocationsRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                addLocation(dataSnapshot.getKey(), dataSnapshot);
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
            public void onChildAdded(DataSnapshot ds, String previousChildKey) {
                addLocation(ds.getKey(), ds);
            }

            @Override
            public void onChildChanged(DataSnapshot ds, String previousChildKey) {
                removeLocation(previousChildKey);
                addLocation(ds.getKey(), ds);
            }

            @Override
            public void onChildRemoved(DataSnapshot ds) {
                removeLocation(ds.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot ds, String previousChildKey) {
                System.out.println(previousChildKey);
            }
        });
    }

    private void addLocation(String key, DataSnapshot ds) {
        Location location = ds.getValue(Location.class);
        NavigationButton button = new NavigationButton(location.getDescription());
        button.addClickListener((NavigationButton.NavigationButtonClickEvent event) -> {
            getNavigationManager().navigateTo(new ItemView());
        });
        content.addComponent(button);
        buttons.put(key, button);
        locations.put(key, location);
        content.markAsDirty();
    }

    private void removeLocation(String key) {
        content.removeComponent(buttons.get(key));
        buttons.remove(key);
        locations.remove(key);
        content.markAsDirty();
    }
}
