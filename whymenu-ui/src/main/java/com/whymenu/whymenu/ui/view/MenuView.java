package com.whymenu.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.whymenu.data.Location;
import com.whymenu.service.LocationService;
import com.whymenu.whymenu.ui.WhymenuUI;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class MenuView extends NavigationView {

    private Map<String, NavigationButton> buttons;
    private final LocationService locationService;
    private VerticalComponentGroup content;

    public MenuView() {
        locationService = new LocationService();
        init();
    }

    private void init() {
        setCaption("Menu");
        content = new VerticalComponentGroup();
        buttons = new HashMap<>();
        locationService.loadLocations().stream().filter((location) -> (!buttons.containsKey(location.getName()))).forEach((location) -> {
            addLocation(location);
        });
        setContent(content);
    }
    
    private void addLocation(Location location) {
        NavigationButton button = new NavigationButton(location.getName());
        button.setDescription(location.getDescription());
        button.addClickListener((NavigationButton.NavigationButtonClickEvent event) -> {
            WhymenuUI.getApp().setLocation(location);
            getNavigationManager().navigateTo(new MenuItemView(location));
                    //((NavigationButton) event.getSource()).getCaption()));
        });
        content.addComponent(button);
        buttons.put(location.getName(), button);
    }
    
}
