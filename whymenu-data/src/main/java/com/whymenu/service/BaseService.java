/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.whymenu.app.SheetsQuickstart;
import static com.whymenu.app.SheetsQuickstart.getSheetsService;
import java.io.IOException;
import java.io.InputStream;
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
    protected static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);
    protected Credential credential;
    protected Sheets service;
    protected String spreadsheetId;
    protected HashMap<String, Integer> columns;
    protected List<ValueRange> values;
    protected List<String> ranges;

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
        } catch (IOException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void authorizeWithServiceAccount() throws IOException {
        InputStream in
                = SheetsQuickstart.class.getResourceAsStream("/service_account.json");
        credential = GoogleCredential.fromStream(in)
                .createScoped(SCOPES);
    }

    protected String getStringValue(List<Object> row, int index) {
        String result = "";
        if (row.size() >= (index - 1)) {
            if (row.get(index) != null) {
                result = row.get(index).toString().trim();
            }
        }
        return result;
    }

}
