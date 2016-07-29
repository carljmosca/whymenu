package com.whymenu.whymenu.ui;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.whymenu.service.FirebaseService;

@SuppressWarnings("serial")
@WebServlet("/*")
public class WhymenuServlet extends TouchKitServlet {

    private final WhymenuUIProvider uiProvider = new WhymenuUIProvider();
    private FirebaseService firebaseService;
    public static final String FIREBASE_DATABASE_NAME = "FIREBASE_DATABASE_NAME";

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        FirebaseService.getInstance();
    }

}
