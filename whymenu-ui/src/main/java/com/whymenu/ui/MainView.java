/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author moscac
 */
public class MainView extends Panel implements View {

    private static final long serialVersionUID = -4533174411254994925L;
    private VerticalLayout mainLayout;
    public static final String NAME = "main";
    
    public MainView() {
        init();
    }

    private void init() {
        buildMainLayout();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private void buildMainLayout() {
        setSizeFull();
        mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        addComponents();
        setContent(mainLayout);
        bind();
    }

    private void addComponents() {
        mainLayout.addComponent(new Label("test"));
    }
    
    private void bind() {
//        fieldGroup = new BeanFieldGroup<>(Template.class);
//        fieldGroup.setItemDataSource(templateManager.getTemplateBeanItem().getBean());
//        fieldGroup.bind(tfMaxImportRecords, "maxImportRecords");
//        fieldGroup.bindMemberFields(templateGridHeaderLayout);
    }

}
