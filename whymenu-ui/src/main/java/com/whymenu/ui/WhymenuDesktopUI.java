package com.whymenu.ui;

import com.vaadin.addon.touchkit.annotations.CacheManifestEnabled;
import com.vaadin.addon.touchkit.annotations.OfflineModeEnabled;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * This UI is served for browsers that don't support TouchKit.
 */
@SuppressWarnings("serial")
// Disable browser caching the app for running it when offline
@CacheManifestEnabled(false)
// Prevent showing OfflineMode client UI if network fails
@OfflineModeEnabled(false)
public class WhymenuDesktopUI extends UI {
    
    private Navigator navigator;
    private MainView mainView;

    @Override
    protected void init(VaadinRequest request) {
        //request.getUserPrincipal().getName();
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        mainView = new MainView();
        navigator = new Navigator(this, this);
        this.navigator.setErrorView(mainView);

        // Create and register the views
        navigator.addView(MainView.NAME, mainView);

        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
                String email = (String)VaadinSession.getCurrent().getSession().getAttribute("email");
                if (event.getNewView() instanceof MainView
                        && email == null) {
                    getPage().open("/whymenu-ui/login", "_self");
                    return false;
                }
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeListener.ViewChangeEvent event) {
            }
        });

        setContent(layout);
    }

}
