/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.ui;

import com.auth0.Auth0User;
import com.whymenu.util.Utility;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author moscac
 */
public class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = -3096880835746361361L;
    protected String auth0Domain;
    protected String auth0ClientId;
    protected String auth0ClientSecret;
    protected String authorizedDomains;
    protected String authorizedEmails;
    protected String redirectUri;
    protected Auth0User auth0User;
    protected final static Logger LOGGER = Logger.getLogger(BaseServlet.class.getName());

    protected String getLoginUri() {
        setup();
        StringBuilder url = new StringBuilder();
        url.append("https://").append(auth0Domain).append("/authorize/?"); //authorize
        url.append("response_type=").append("code");
        url.append("&client_id=").append(auth0ClientId);
        url.append("&redirect_uri=").append(redirectUri); // http://localhost:8080/callback/");
        //url.append("&state=STATE");
        url.append("&scope=openid");
        LOGGER.log(Level.FINE, "getLoginUri: {0}", url.toString());
        return url.toString();
    }

    private void setup() {
        auth0Domain = Utility.getEnvironmentOrPropertyVariables("AUTH0_DOMAIN");
        auth0ClientId = Utility.getEnvironmentOrPropertyVariables("AUTH0_CLIENT_ID");
        auth0ClientSecret = Utility.getEnvironmentOrPropertyVariables("AUTH0_CLIENT_SECRET");
        redirectUri = Utility.getEnvironmentOrPropertyVariables("REDIRECT_URI");
        authorizedDomains = Utility.getEnvironmentOrPropertyVariables("AUTHORIZED_DOMAINS");
        authorizedEmails = Utility.getEnvironmentOrPropertyVariables("AUTHORIZED_EMAILS");
    }

    protected URI getBaseUri(HttpServletRequest request) {
        return URI.create(request.getRequestURL().toString()).resolve(request.getContextPath());
    }
}
