/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout; 
import com.vaadin.ui.Table;
import com.whymenu.data.CustomerOrderLine;
import com.whymenu.whymenu.ui.WhymenuUI;
import com.whymenu.whymenu.util.SessionManager;

/**
 *
 * @author moscac
 */
public class CustomerOrderView extends NavigationView {

    private Table tblCustomerOrder;
    private final BeanItemContainer<CustomerOrderLine> customerOrderLines;
   
    public CustomerOrderView() {
        customerOrderLines = new BeanItemContainer<>(CustomerOrderLine.class);
        init();
    }
        
    @Override
    public void attach() {
        super.attach();
        refresh();
    }
    
    private void refresh() {
        customerOrderLines.removeAllItems();
        WhymenuUI.getApp().getCustomerOrder().getCustomerOrderLines().stream().forEach((customerOrderLine) -> {
            customerOrderLines.addBean(customerOrderLine);
        });
        tblCustomerOrder.refreshRowCache();
    }

    private void init() {
        final VerticalComponentGroup content = new VerticalComponentGroup();
        tblCustomerOrder = new Table();
        tblCustomerOrder.setContainerDataSource(customerOrderLines);
        tblCustomerOrder.setImmediate(true);
        tblCustomerOrder.setVisibleColumns(new Object[] { "description", "quantity",
                "price"});
        tblCustomerOrder.setColumnHeader("description", "Description");
        tblCustomerOrder.setColumnHeader("quantity", "Qty");
        tblCustomerOrder.setColumnHeader("price", "Price");
        content.addComponent(tblCustomerOrder);
        final Button submitButton = new Button("Submit Order");
        submitButton.addClickListener((Button.ClickEvent event) -> {
            getNavigationManager().navigateBack();
        });
        setContent(new CssLayout(content, submitButton));
    }

}
