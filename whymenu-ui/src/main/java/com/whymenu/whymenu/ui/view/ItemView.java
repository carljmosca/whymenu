package com.whymenu.whymenu.ui.view;

import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ItemView extends NavigationView {

    public ItemView() {
        init();
    }
    
    private void init() {
        setCaption("Item");
        final VerticalComponentGroup content = new VerticalComponentGroup();

        final TextField nameField = new TextField("Name");
        nameField.setInputPrompt("Enter your name...");
        content.addComponent(nameField);

        final DatePicker dateField = new DatePicker("Date of Birth");
        content.addComponent(dateField);

        final EmailField emailField = new EmailField("Email");
        emailField.setInputPrompt("Enter your email address...");
        content.addComponent(emailField);

        final Button submitButton = new Button("Submit");
        submitButton.addClickListener((ClickEvent event) -> {
            Notification.show("Thanks!");
        });

        setContent(new CssLayout(content, submitButton));
    }

}
