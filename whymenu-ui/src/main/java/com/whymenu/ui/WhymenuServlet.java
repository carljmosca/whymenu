package com.whymenu.ui;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.settings.TouchKitSettings;
import com.vaadin.server.SessionInitEvent;

@WebServlet("/*")
public class WhymenuServlet extends TouchKitServlet {

    private final WhymenuUIProvider uiProvider = new WhymenuUIProvider();

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();

        TouchKitSettings touchKitSettings = getTouchKitSettings();

        getService().addSessionInitListener((SessionInitEvent event) -> {
            event.getSession().addUIProvider(uiProvider);
        });

        touchKitSettings.getApplicationCacheSettings().setCacheManifestEnabled(true);
        
    }

}
