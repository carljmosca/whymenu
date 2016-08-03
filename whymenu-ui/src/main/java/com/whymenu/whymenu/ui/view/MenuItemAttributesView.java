/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.whymenu.data.MenuItem;
import com.whymenu.data.MenuItemAttribute;
import com.whymenu.data.MenuItemAttributeOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class MenuItemAttributesView extends NavigationView implements Component.Listener {

    private final MenuItem menuItem;
    private final List<ListSelect> listSelects;
    private Label label;

    public MenuItemAttributesView(MenuItem menuItem) {
        this.menuItem = menuItem;
        listSelects = new ArrayList<>();
        init();
    }

    private void init() {
        setCaption(menuItem.getName());
        final VerticalComponentGroup content = new VerticalComponentGroup();
        for (MenuItemAttribute menuItemAttribute : menuItem.getAttributes()) {
            ListSelect listSelect = new ListSelect(menuItemAttribute.getDescription());
            listSelect.addListener(this);
            listSelect.setImmediate(true);
            listSelect.addItems(menuItemAttribute.getDetails());
            listSelect.setMultiSelect(menuItemAttribute.isMultiSelect());
            content.addComponent(listSelect);
            listSelects.add(listSelect);
        }
        label = new Label();
        label.setImmediate(true);
        content.addComponent(label);
        final Button submitButton = new Button("Add to order");
        submitButton.addClickListener((Button.ClickEvent event) -> {
            Notification.show("Thanks!");
        });
        content.addComponent(submitButton);
        setContent(content);
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    @Override
    public void componentEvent(Event event) {
        updateOrderedItem();
    }

    private void updateOrderedItem() {
        StringBuilder orderItemDescription = new StringBuilder();
        orderItemDescription.append(menuItem.getName()).append(menuItem.getDescription()).append(":");
        listSelects.stream().filter((listSelect) -> (listSelect.getValue() != null)).map((listSelect) -> (MenuItemAttributeOption[]) listSelect.getValue()).forEach((options) -> {
            for (MenuItemAttributeOption option : options) {
                orderItemDescription.append(option.getDescription()).append(" ");
            }
        });
        label.setValue(orderItemDescription.toString().trim());
    }

}
