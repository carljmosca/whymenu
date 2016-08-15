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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.wcs.wcslib.vaadin.widget.recaptcha.ReCaptcha;
import com.wcs.wcslib.vaadin.widget.recaptcha.shared.ReCaptchaOptions;
import com.whymenu.data.CustomerOrderLine;
import com.whymenu.util.Utility;
import com.whymenu.whymenu.ui.WhymenuUI;
import java.io.Serializable;

/**
 *
 * @author moscac
 */
public class CustomerOrderView extends NavigationView implements Serializable {

    private Table tblCustomerOrder;
    private final BeanItemContainer<CustomerOrderLine> customerOrderLines;
    private ReCaptcha reCaptcha;
    private VerticalComponentGroup content;
    private Button submitButton;

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
        content.removeAllComponents();
        content.addComponent(tblCustomerOrder);
        content.addComponent(reCaptcha);
        setContent(new CssLayout(content, submitButton));
    }

    private void init() {
        content = new VerticalComponentGroup();
        tblCustomerOrder = new Table();
        tblCustomerOrder.setContainerDataSource(customerOrderLines);
        tblCustomerOrder.setImmediate(true);
        tblCustomerOrder.setHeight("150px");
        tblCustomerOrder.setVisibleColumns(new Object[]{"description", "quantity",
            "price"});
        tblCustomerOrder.setColumnHeader("description", "Description");
        tblCustomerOrder.setColumnHeader("quantity", "Qty");
        tblCustomerOrder.setColumnHeader("price", "Price");
        reCaptcha = addRecaptcha();
        submitButton = new Button("Submit Order");
        submitButton.addClickListener((Button.ClickEvent event) -> {
            if (!reCaptcha.validate()) {
                Notification.show("Invalid!", Notification.Type.ERROR_MESSAGE);
                reCaptcha.reload();
            } else {
                content.removeComponent(reCaptcha);
                Notification.show("Thank you", Notification.Type.HUMANIZED_MESSAGE); // ASSISTIVE_NOTIFICATION);
            }
        });
    }

    private ReCaptcha addRecaptcha() {
        return new ReCaptcha(
                Utility.getEnvironmentOrPropertyVariables("RECAPTCHA_PRIVATE_KEY"),
                new ReCaptchaOptions() {
            {
                theme = "light";
                sitekey = Utility.getEnvironmentOrPropertyVariables("RECAPTCHA_SITE_KEY");
            }
        }
        );
    }

}
