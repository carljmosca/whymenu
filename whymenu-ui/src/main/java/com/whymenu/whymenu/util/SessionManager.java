/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.whymenu.util;

import com.whymenu.data.CustomerOrder;
import com.whymenu.whymenu.ui.WhymenuUI;

/**
 *
 * @author moscac
 */
public class SessionManager {

//    public static CustomerOrder getCustomerOrder() {
////        CustomerOrder customerOrder;
////        try {
////            VaadinSession.getCurrent().getLockInstance().lock();
////            customerOrder = VaadinSession.getCurrent().getAttribute(CustomerOrder.class);
////            if (customerOrder == null) {
////                customerOrder = new CustomerOrder();
////            }
////        } finally {
////            VaadinSession.getCurrent().getLockInstance().unlock();
////        }
//        return WhymenuUI.getApp().getCustomerOrder();
//    }

//    public static void saveCustomerOrder(CustomerOrder customerOrder) {
//        try {
//            VaadinSession.getCurrent().getLockInstance().lock();
//            VaadinSession.getCurrent().setAttribute(CustomerOrder.class, customerOrder);
//        } finally {
//            VaadinSession.getCurrent().getLockInstance().unlock();
//        }
//    }

//    public static String getServiceAccountFileName() {
//        ServletContext servletContext = VaadinServlet.getCurrent().getServletContext();
//        return servletContext.getRealPath("file://usr/local/whymenu/whymenu_service_account.json");
//        //is = BaseService.class.getResourceAsStream("file://usr/local/whymenu/whymenu_service_account.json");
//
//    }
}
