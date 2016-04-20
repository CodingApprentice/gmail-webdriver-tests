package com.appsenseca.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by SunilM on 20/04/2016.
 */
public class EmailViewPage {

    public static String getNewEmailSubjectText(WebDriver driver) {

        String newEmailSubjectText = driver.findElement(By.cssSelector("td div div div h2")).getText();

        return newEmailSubjectText;
    }
}
