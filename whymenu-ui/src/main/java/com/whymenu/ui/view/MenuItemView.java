package com.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.whymenu.data.Location;
import com.whymenu.data.MenuItem;
import com.whymenu.service.sheets.MenuItemService;
import java.util.HashMap;

@SuppressWarnings("serial")
public class MenuItemView extends NavigationView {

    private final MenuItemService menuItemService;
    private final Location location;
    private final HashMap<String, MenuItemAttributesView> menuItemAttributesViews;

    public MenuItemView(Location location) {
        this.location = location;
        menuItemAttributesViews = new HashMap<>();
        menuItemService = new MenuItemService();
        init();
    }

    private void init() {
        setCaption("Item");
        final VerticalComponentGroup content = new VerticalComponentGroup();

        for (MenuItem menuItem : menuItemService.loadMenuItems(location.getName())) {
            NavigationButton button = new NavigationButton(menuItem.getName());
            menuItemAttributesViews.put(menuItem.getName(), new MenuItemAttributesView(menuItem));
            button.addClickListener((NavigationButton.NavigationButtonClickEvent event) -> {
                getNavigationManager().navigateTo(menuItemAttributesViews.get(button.getCaption()));
            });
            content.addComponent(button);
        }
        
//        menuItemService.loadMenuItems(location).stream().map((menuItem) -> {
//            NavigationButton button = new NavigationButton(menuItem.getName());
//            if (menuItemAttributesView == null || 
//                    !menuItemAttributesView.getMenuItem().getName().equals(menuItem.getName())) {
//                menuItemAttributesView = new MenuItemAttributesView(menuItem);
//            }
//            button.addClickListener((NavigationButton.NavigationButtonClickEvent event) -> {
//                getNavigationManager().navigateTo(menuItemAttributesView);
//            });
//            return button;
//        }).forEach((button) -> {
//            content.addComponent(button);
//        });

//        final TextField nameField = new TextField("Name");
//        nameField.setInputPrompt("Enter your name...");
//        content.addComponent(nameField);
//
//        final DatePicker dateField = new DatePicker("Date of Birth");
//        content.addComponent(dateField);
//
//        final EmailField emailField = new EmailField("Email");
//        emailField.setInputPrompt("Enter your email address...");
//        content.addComponent(emailField);
//
        final Button submitButton = new Button("Submit");
        submitButton.addClickListener((ClickEvent event) -> {
            Notification.show("Thanks!");
        });
        setContent(new CssLayout(content, submitButton));
    }
}
