/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.util;

/**
 *
 * @author moscac
 */
public class Utility {

    public final static String CHARACTER_SPACE = " ";
    public final static String CHARACTER_DASH = "-";
    public final static String CHARACTER_AMPERSAND = "@";

    public static String getEnvironmentOrPropertyVariables(String name) {
        String result = "";
        if (System.getenv(name) != null) {
            result = System.getenv(name);
        } else if (System.getProperty(name) != null) {
            result = System.getProperty(name);
        }
        return result;
    }
}
