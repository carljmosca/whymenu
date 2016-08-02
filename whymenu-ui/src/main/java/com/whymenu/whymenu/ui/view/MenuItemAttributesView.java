/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.ListSelect;
import com.whymenu.data.MenuItem;
import com.whymenu.data.MenuItemAttribute;

/**
 *
 * @author moscac
 */
public class MenuItemAttributesView extends NavigationView {

    private final MenuItem menuItem;

    public MenuItemAttributesView(MenuItem menuItem) {
        this.menuItem = menuItem;
        init();
    }

    private void init() {
        setCaption(menuItem.getName());
        final VerticalComponentGroup content = new VerticalComponentGroup();
        for (MenuItemAttribute menuItemAttribute : menuItem.getAttributes()) {
            ListSelect listSelect = new ListSelect(menuItemAttribute.getDescription());
            listSelect.addItems(menuItemAttribute.getDetails());
            listSelect.setMultiSelect(menuItemAttribute.isMultiSelect());
            content.addComponent(listSelect);
        }
        setContent(content);
    }

}
