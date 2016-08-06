/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;

/**
 *
 * @author moscac
 */
public class CustomerOrderView extends NavigationView {

    public CustomerOrderView() {
        init();
    }

    private void init() {
        final VerticalComponentGroup content = new VerticalComponentGroup();
        final Button submitButton = new Button("Submit Order");
        submitButton.addClickListener((Button.ClickEvent event) -> {
            getNavigationManager().navigateBack();
        });
        setContent(new CssLayout(content, submitButton));
    }

}
