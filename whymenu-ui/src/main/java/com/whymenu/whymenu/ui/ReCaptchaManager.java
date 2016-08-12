/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.whymenu.ui;

import com.wcs.wcslib.vaadin.widget.recaptcha.ReCaptcha;
import com.wcs.wcslib.vaadin.widget.recaptcha.shared.ReCaptchaOptions;
import com.whymenu.util.Utility;

/**
 *
 * @author moscac
 */
public class ReCaptchaManager {

    public ReCaptcha addRecaptcha() {
        ReCaptcha captcha = new ReCaptcha(
                Utility.getEnvironmentOrPropertyVariables("RECAPTCHA_PRIVATE_KEY"),
                new ReCaptchaOptions() {
            {
                theme = "light";
                sitekey = Utility.getEnvironmentOrPropertyVariables("RECAPTCHA_SITE_KEY");
            }
        }
        );
        return captcha;
    }
}
