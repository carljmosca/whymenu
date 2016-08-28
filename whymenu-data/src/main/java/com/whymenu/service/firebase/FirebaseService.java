/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moscac
 */
public class FirebaseService {
    private String firebaseDatabaseName;
    private com.whymenu.service.firebase.FirebaseService firebaseService;
    public static final String FIREBASE_DATABASE_NAME = "FIREBASE_DATABASE_NAME";
    public static final String FIREBASE_SERVICE_ACCOUNT = "FIREBASE_SERVICE_ACCOUNT";
    private static FirebaseService instance;

    public static FirebaseService getInstance() {
        if (instance == null) {
            instance = new FirebaseService();
        }
        return instance;
    }

    private FirebaseService() {
        try {
            init();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() throws FileNotFoundException {
        if (System.getenv(FIREBASE_DATABASE_NAME) != null) {
            firebaseDatabaseName = System.getenv(FIREBASE_DATABASE_NAME);
        } else if (System.getProperty(FIREBASE_DATABASE_NAME) != null) {
            firebaseDatabaseName = System.getProperty(FIREBASE_DATABASE_NAME);
        }
        String firebaseServiceAccount = System.getProperty(FIREBASE_SERVICE_ACCOUNT);
        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setServiceAccount(new FileInputStream("/Users/moscac/Downloads/orderappdata-aaae12d0366b.json"))
                .setServiceAccount(new ByteArrayInputStream(firebaseServiceAccount.getBytes()))
                .setDatabaseUrl("https://" + firebaseDatabaseName + ".firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);
    }

}
