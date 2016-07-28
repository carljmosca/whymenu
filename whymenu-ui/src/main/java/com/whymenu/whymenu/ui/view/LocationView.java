/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.whymenu.ui.view;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.whymenu.data.Location;
import com.whymenu.service.LocationService;

/**
 *
 * @author moscac
 */
public class LocationView extends NavigationView {

    private LocationService locationService;

    public LocationView() {
        init();
    }

    private void init() {
        locationService = new LocationService();
        addComponents();
    }

    private void addComponents() {

        locationService.getLocationsRef().addChildEventListener(new ChildEventListener() {

            @Override
            public void onCancelled(DatabaseError de) {
            }

            @Override
            public void onChildAdded(DataSnapshot ds, String string) {
                Location location = ds.getValue(Location.class);
                System.out.println(location.getDescription());
            }

            @Override
            public void onChildChanged(DataSnapshot ds, String string) {
                Location location = ds.getValue(Location.class);
                System.out.println(location.getDescription());
            }

            @Override
            public void onChildRemoved(DataSnapshot ds) {
            }

            @Override
            public void onChildMoved(DataSnapshot ds, String string) {
            }
        });
    }
}
