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
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.whymenu.data.MenuItem;
import com.whymenu.data.MenuItemAttribute;
import com.whymenu.data.MenuItemAttributeOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author moscac
 */
public class MenuItemAttributesView extends NavigationView implements Component.Listener {

    private final MenuItem menuItem;
    private final List<ListSelect> listSelects;
    private final Label label;
    private boolean complete;

    public MenuItemAttributesView(MenuItem menuItem) {
        this.menuItem = menuItem;
        listSelects = new ArrayList<>();
        label = new Label();
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
        label.setImmediate(true);
        label.setHeightUndefined();
        final Button submitButton = new Button("Add to order");
        submitButton.addClickListener((Button.ClickEvent event) -> {
            complete = true;
            getNavigationManager().navigateBack();
        });
        setContent(new CssLayout(content, label, submitButton));
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
        orderItemDescription.append(menuItem.getName()).append(" ");
        if (!menuItem.getName().equals(menuItem.getDescription())) {
            orderItemDescription.append(menuItem.getDescription()).append(" ");
        }
        listSelects.stream().map((listSelect) -> {
            return listSelect;
        }).forEach((ListSelect listSelect) -> {
            for (Object object : (Collection) listSelect.getValue()) {
                if (object instanceof MenuItemAttributeOption) {
                    MenuItemAttributeOption option = (MenuItemAttributeOption) object;
                    orderItemDescription.append(option.getDescription()).append(" ");
                }
            }
        });

        listSelects.stream().filter((listSelect) -> (listSelect.getValue() instanceof MenuItemAttributeOption[])).forEach((listSelect) -> {
            for (MenuItemAttributeOption menuItemAttributeOption : (MenuItemAttributeOption[]) listSelect.getValue()) {
                if (menuItemAttributeOption.getDescription() != null
                        && !menuItemAttributeOption.getDescription().trim().isEmpty()) {
                    orderItemDescription.append(menuItemAttributeOption.getDescription()).append(" ");
                }
            }
        });
        label.setCaption(orderItemDescription.toString().trim());
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        if (complete) {
            clearSelections();
        }
    }
    
    private void clearSelections() {
        for (ListSelect listSelect : listSelects) {
            listSelect.setValue(null);
        }
    }

}
