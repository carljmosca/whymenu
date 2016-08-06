/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moscac
 */
public class BaseService {

    public static final String WHYMENU_SPREADSHEET_ID = "WHYMENU_SPREADSHEET_ID";
    public static final String WHYMENU_SERVICE_ACCOUNT = "WHYMENU_SERVICE_ACCOUNT";
    protected static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);
    protected Credential credential;
    protected Sheets service;
    protected String spreadsheetId;
    protected HashMap<String, Integer> columns;
    protected List<ValueRange> values;
    protected List<String> ranges;

    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;

    public BaseService() {
        try {
            authorizeWithServiceAccount();
            service = getSheetsService();
            if (System.getenv(WHYMENU_SPREADSHEET_ID) != null) {
                spreadsheetId = System.getenv(WHYMENU_SPREADSHEET_ID);
            } else if (System.getProperty(WHYMENU_SPREADSHEET_ID) != null) {
                spreadsheetId = System.getProperty(WHYMENU_SPREADSHEET_ID);
            }
            columns = new HashMap<>();
            ranges = new ArrayList<>();
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Build and return an authorized Sheets API client service.
     *
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    private Sheets getSheetsService() throws IOException, GeneralSecurityException {
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private void authorizeWithServiceAccount() throws IOException {
        String whymenuServiceAccount = System.getenv(WHYMENU_SERVICE_ACCOUNT);
        InputStream is = new ByteArrayInputStream(whymenuServiceAccount.getBytes());
        //InputStream in = BaseService.class.getResource AsStream(is);
        //        = BaseService.class.getResourceAsStream("/service_account.json");
        credential = GoogleCredential.fromStream(is)
                .createScoped(SCOPES);
    }

    protected String getStringValue(List<Object> row, String columnKey) {
        if (columns.containsKey(columnKey)) {
            int index = columns.get(columnKey);
            return getStringValue(row, index);
        }
        return "";
    }

    protected String getStringValue(List<Object> row, int index) {
        String result = "";
        if (row.size() >= (index + 1)) {
            if (row.get(index) != null) {
                result = row.get(index).toString().trim();
            }
        }
        return result;
    }

    protected Integer getIntValue(List<Object> row, String columnKey) {
        String value = getStringValue(row, columnKey);
        Integer result = 0;
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {

        }
        return result;
    }

    protected boolean getBooleanValue(List<Object> row, String columnKey) {
        boolean result = true;
        String value = getStringValue(row, columnKey).toLowerCase();
        result = !"false".equals(value) && !"no".equals(value) && !"0".equals(value);
        return result;
    }

}
