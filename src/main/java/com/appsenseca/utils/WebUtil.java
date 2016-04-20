package com.appsenseca.utils;

import com.appsenseca.pageobjects.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
/**
 * Created by SunilM on 20/04/2016.
 */
public class WebUtil {

    public static SignInPage goToSignInPage(WebDriver driver) {
        driver.navigate().to("http://gmail.com");
        return PageFactory.initElements(driver, SignInPage.class);
    }
}
