/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.whymenu.ui.view;

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
    private Map<String, NavigationButton> buttons;
    private LocationService locationService;

    public LocationView() {
        init();
    }

    private void init() {
        content = new VerticalComponentGroup();
        buttons = new HashMap<>();
        locationService = new LocationService();
        for (Location location : locationService.loadLocations()) {
            if (!buttons.containsKey(location.getName())) {
                addLocation(location.getName(), location);
            }
        }
    }

    private void addLocation(String key, Location location) {
        NavigationButton button = new NavigationButton(location.getDescription());
        button.addClickListener((NavigationButton.NavigationButtonClickEvent event) -> {
            getNavigationManager().navigateTo(new ItemView());
        });
        content.addComponent(button);
        buttons.put(key, button);
    }

    private void removeLocation(String key) {
        content.removeComponent(buttons.get(key));
        buttons.remove(key);
    }
}
