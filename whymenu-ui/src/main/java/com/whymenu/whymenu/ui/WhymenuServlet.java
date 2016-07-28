package com.whymenu.whymenu.ui;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.server.SessionInitEvent;

@SuppressWarnings("serial")
@WebServlet("/*")
public class WhymenuServlet extends TouchKitServlet {

    private final WhymenuUIProvider uiProvider = new WhymenuUIProvider();

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        getService().addSessionInitListener((SessionInitEvent event) -> {
            event.getSession().addUIProvider(uiProvider);
        });
    }

}
