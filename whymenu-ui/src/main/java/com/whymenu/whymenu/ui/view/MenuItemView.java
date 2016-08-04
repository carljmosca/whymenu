package com.whymenu.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.whymenu.service.MenuItemService;

@SuppressWarnings("serial")
public class MenuItemView extends NavigationView {

    private final MenuItemService menuItemService;
    private final String location;
    private MenuItemAttributesView menuItemAttributesView;

    public MenuItemView(String location) {
        this.location = location;
        menuItemService = new MenuItemService();
        init();
    }

    private void init() {
        setCaption("Item");
        final VerticalComponentGroup content = new VerticalComponentGroup();

        menuItemService.loadMenuItems(location).stream().map((menuItem) -> {
            NavigationButton button = new NavigationButton(menuItem.getName());
            if (menuItemAttributesView == null || menuItemAttributesView.isComplete()) {
                menuItemAttributesView = new MenuItemAttributesView(menuItem);
            }
            button.addClickListener((NavigationButton.NavigationButtonClickEvent event) -> {
                getNavigationManager().navigateTo(menuItemAttributesView);
            });
            return button;
        }).forEach((button) -> {
            content.addComponent(button);
        });

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
