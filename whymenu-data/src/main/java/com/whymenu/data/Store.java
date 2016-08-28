/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class Store implements Serializable {
    
    private static final long serialVersionUID = 8977842461591529872L;
    private String storeName;
    private String webSite;
    private List<String> authorizedUserEmails;

    public Store() {
        authorizedUserEmails = new ArrayList<>();
    }
    
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public List<String> getAuthorizedUserEmails() {
        return authorizedUserEmails;
    }

    public void setAuthorizedUserEmails(List<String> authorizedUserEmails) {
        this.authorizedUserEmails = authorizedUserEmails;
    }
    
}
