/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.NumberField;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.whymenu.data.CustomerOrderLine;
import com.whymenu.data.MenuItem;
import com.whymenu.data.MenuItemAttribute;
import com.whymenu.data.MenuItemAttributeOption;
import com.whymenu.ui.WhymenuUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author moscac
 */
public class MenuItemAttributesView extends NavigationView implements Component.Listener {

    private MenuItem menuItem;
    private List<ListSelect> lsAttributes;
    private NumberField nfQuantity;
    private Label lblDescription;
    private boolean complete;
    private CustomerOrderLine customerOrderLine;

    public MenuItemAttributesView(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public void attach() {
        complete = false;
        lsAttributes = new ArrayList<>();
        lblDescription = new Label();
        nfQuantity = new NumberField();
        customerOrderLine = new CustomerOrderLine();
        init();
    }

    private void init() {
        setCaption(menuItem.getName());
        customerOrderLine.setItemNumber(menuItem.getItemNumber());
        customerOrderLine.setName(menuItem.getName());
        customerOrderLine.setPrice(menuItem.getPrice());
        customerOrderLine.setTaxRateCode(menuItem.getTaxRateCode());
        final VerticalComponentGroup content = new VerticalComponentGroup();
        for (MenuItemAttribute menuItemAttribute : menuItem.getAttributes()) {
            ListSelect listSelect = new ListSelect(menuItemAttribute.getDescription());
            listSelect.addListener(this);
            listSelect.setImmediate(true);
            for (MenuItemAttributeOption option : menuItemAttribute.getDetails()) {
                if (option.isAvailable()) {
                    listSelect.addItem(option);
                    listSelect.setItemCaption(option, option.getDescription());
                }
            }
            listSelect.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
            listSelect.setMultiSelect(menuItemAttribute.isMultiSelect());
            content.addComponent(listSelect);
            lsAttributes.add(listSelect);
        }
        nfQuantity.setWidth("50px");
        nfQuantity.setImmediate(true);
        nfQuantity.setValue("1");
        nfQuantity.setInputPrompt("Quantity");
        nfQuantity.addListener((Event event) -> {
            try {
                int quantity = Integer.parseInt(nfQuantity.getValue());
                customerOrderLine.setQuantity(quantity);
            } catch (NumberFormatException e) {

            }
        });
        lblDescription.setImmediate(true);
        lblDescription.setHeightUndefined();
        final Button submitButton = new Button("Add to order");
        submitButton.addClickListener((Button.ClickEvent event) -> {
            WhymenuUI.getApp().getCustomerOrder().getCustomerOrderLines().add(customerOrderLine);
            complete = true;
            clearSelections();
            getNavigationManager().navigateBack();
        });
        setContent(new CssLayout(content, nfQuantity, lblDescription, submitButton));
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    @Override
    public void componentEvent(Event event) {
        if (complete) {
            return;
        }
        customerOrderLine.getOptions().clear();
        for (ListSelect listSelect : lsAttributes) {
            if (listSelect.getValue() instanceof Collection) {
                for (Object object : (Collection) listSelect.getValue()) {
                    if (object instanceof MenuItemAttributeOption) {
                        customerOrderLine.getOptions().add((MenuItemAttributeOption) object);
                    }
                }
            }
        }
        lblDescription.setCaption(customerOrderLine.getDescription());
    }

    private void clearSelections() {
        for (ListSelect listSelect : lsAttributes) {
            listSelect.setValue(null);
        }
    }

}
