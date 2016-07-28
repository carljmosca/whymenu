/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author moscac
 */

public class FirebaseService {

    private final String firebaseDatabaseName;
    
    public FirebaseService(String firebaseDatabaseName) throws FileNotFoundException {
        this.firebaseDatabaseName = firebaseDatabaseName;
        init();
    }
    
    private void init() throws FileNotFoundException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("/Users/moscac/Downloads/orderappdata-aaae12d0366b.json"))
                .setDatabaseUrl("https://" + firebaseDatabaseName + ".firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);
    }

}
