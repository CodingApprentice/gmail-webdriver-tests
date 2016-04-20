package com.appsenseca.utils;

import com.appsenseca.pageobjects.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by SunilM on 20/04/2016.
 */
public class WebUtil {

    public static SignInPage goToSignInPage(WebDriver driver) {
        driver.navigate().to("http://gmail.com");
        return PageFactory.initElements(driver, SignInPage.class);
    }

    public static void click(WebDriver driver, By by, int timeOut) {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        WebElement element = driver.findElement(by);
        element.click();
    }
}
