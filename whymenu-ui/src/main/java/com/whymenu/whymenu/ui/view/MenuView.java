package com.whymenu.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickEvent;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickListener;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;

@SuppressWarnings("serial")
public class MenuView extends NavigationView {

    private LocationView locationView;
    
    public MenuView() {
        setCaption("Menu");
        locationView = new LocationView();
        final VerticalComponentGroup content = new VerticalComponentGroup();
        NavigationButton button = new NavigationButton("Form");
        button.addClickListener(new NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButtonClickEvent event) {
//                getNavigationManager().navigateTo(new ItemView());
                getNavigationManager().navigateTo(locationView);
            }
        });
        content.addComponent(button);
        setContent(content);
    };

    public LocationView getLocationView() {
        return locationView;
    }
    
}
