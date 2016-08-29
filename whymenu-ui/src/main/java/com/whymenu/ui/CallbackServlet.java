/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import com.auth0.Auth0User;
import com.auth0.Tokens;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 *
 * @author moscac
 */
@WebServlet("/callback")
public class CallbackServlet extends BaseServlet {

    private static final long serialVersionUID = 2979805466286201789L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String token = request.getParameter("token");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", auth0ClientId));
        params.add(new BasicNameValuePair("redirect_uri", redirectUri));
        params.add(new BasicNameValuePair("client_secret", auth0ClientSecret));
        if (code != null) {
            params.add(new BasicNameValuePair("code", code));
        }
        if (token != null) {
            params.add(new BasicNameValuePair("access_token", token));
        }
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));

        try {
            auth0User = getUserFromTokens(params);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        if (emailAddressIsAuthorized()) {
            request.getSession().setAttribute("email", auth0User.getEmail());
            response.sendRedirect(getBaseUri(request).toString() + "/#!" + MainView.NAME);
        } else {
            response.sendRedirect(getLoginUri());
        }
    }

    private boolean emailAddressIsAuthorized() {
        if (auth0User == null || auth0User.getEmail() == null) {
            return false;
        }
        return true;
//        for (String domain : authorizedDomains.split(",")) {
//            if (auth0User.getEmail().toLowerCase().endsWith("@" + domain.trim().toLowerCase())) {
//                return true;
//            }
//        }
//        for (String email : authorizedEmails.split(",")) {
//            if (auth0User.getEmail().toLowerCase().equals(email.trim().toLowerCase())) {
//                return true;
//            }
//        }
//        return false;
    }

    private Auth0User getUserFromTokens(List<NameValuePair> params) throws UnsupportedEncodingException, IOException {

        Auth0User auth0user = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String uri = "https://" + auth0Domain + "/oauth/token";
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            String result = stringFromResponse(client.execute(httpPost));
            try {
                Tokens tokens = new Tokens(new JSONObject(result));
                uri = "https://" + auth0Domain + "/userinfo?access_token=" + tokens.getAccessToken();
                HttpGet httpGet = new HttpGet(uri);
                result = stringFromResponse(client.execute(httpGet));
                auth0user = new Auth0User(new JSONObject(result));
            } catch (JSONException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return auth0user;
    }

    private String stringFromResponse(CloseableHttpResponse response) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {

        }
        return result.toString();
    }
}
