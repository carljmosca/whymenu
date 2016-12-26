package com.whymenu.ui;

import com.whymenu.ui.gwt.client.WhymenuPersistToServerRpc;
import com.whymenu.ui.view.MenuView;

import com.vaadin.addon.touchkit.annotations.CacheManifestEnabled;
import com.vaadin.addon.touchkit.annotations.OfflineModeEnabled;
import com.vaadin.addon.touchkit.extensions.OfflineMode;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationEvent;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.addon.touchkit.ui.TabBarView.SelectedTabChangeListener;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.whymenu.data.CustomerOrder;
import com.whymenu.data.Location;
import com.whymenu.ui.view.CustomerOrderView;

/**
 * The UI's "main" class
 */
@Widgetset("com.whymenu.ui.gwt.WhymenuWidgetSet")
@Theme("whymenu")
// Cache static application files so as the application can be started
// and run even when the network is down.
@CacheManifestEnabled
// Switch to the OfflineMode client UI when the server is unreachable
@OfflineModeEnabled
// Make the server retain UI state whenever the browser reloads the app
@PreserveOnRefresh
public class WhymenuUI extends UI {

    private MenuView menuView;
    private CustomerOrderView customerOrderView;
    private CustomerOrder customerOrder;
    private Location location;
    private boolean reCaptchaValidated;

    private final WhymenuPersistToServerRpc serverRpc = () -> {
        // TODO this method is called from client side to store offline data
    };

    @Override
    protected void init(VaadinRequest request) {
        final TabBarView tabBarView = new TabBarView();
        final NavigationManager navigationManager = new NavigationManager();
        navigationManager.setCaption("Menu");
        menuView = new MenuView();
        navigationManager.setCurrentComponent(menuView);
        Tab tab;
        tab = tabBarView.addTab(navigationManager);
        tab.setIcon(FontAwesome.TASKS);
        customerOrderView = new CustomerOrderView();
        navigationManager.addComponent(customerOrderView);
        tab = tabBarView.addTab(customerOrderView, "Order");
        tab.setIcon(FontAwesome.PENCIL_SQUARE);
        tab = tabBarView.addTab(new Label("About"), "About");
        tab.setIcon(FontAwesome.DOWNLOAD);
        setContent(tabBarView);

        // Use of the OfflineMode connector is optional.
        OfflineMode offlineMode = new OfflineMode();
        offlineMode.extend(this);
        // Maintain the session when the browser app closes.
        offlineMode.setPersistentSessionCookie(true);
        // Define the timeout in secs to wait when a server request is sent
        // before falling back to offline mode.
        offlineMode.setOfflineModeTimeout(15);

        tabBarView.addListener((SelectedTabChangeListener) (TabBarView.SelectedTabChangeEvent event) -> {
            if (event.getTabSheet().getSelelectedTab().getComponent().equals(customerOrderView)) {
                //customerOrderView.refresh();
            }
        });

        navigationManager.addNavigationListener((NavigationEvent event) -> {
            if (navigationManager.getCurrentComponent().equals(customerOrderView)) {
                //customerOrderView.refresh();
            }
            if (event.getDirection() == NavigationEvent.Direction.BACK) {
                // Do something
                //Notification.show("You came back to "
                //        + manager.getCurrentComponent().getCaption());
            }
        });
    }

    public CustomerOrder getCustomerOrder() {
        if (customerOrder == null) {
            customerOrder = new CustomerOrder();
        }
        return customerOrder;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public static WhymenuUI getApp() {
        return (WhymenuUI) UI.getCurrent();
    }

    public boolean isReCaptchaValidated() {
        return reCaptchaValidated;
    }

    public void setReCaptchaValidated(boolean reCaptchaValidated) {
        this.reCaptchaValidated = reCaptchaValidated;
    }

}
