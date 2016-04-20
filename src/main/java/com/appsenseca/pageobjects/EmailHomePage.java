package com.appsenseca.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by SunilM on 20/04/2016.
 */
public class EmailHomePage {

    WebDriverWait wait;

    public SignInPage SignOut(WebDriver driver) {

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title*='Google Account']")));

        WebElement accountLogo = driver.findElement(By.cssSelector("[title*='Google Account']"));
        accountLogo.click();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Sign out")));
        WebElement signOutButton = driver.findElement(By.partialLinkText("Sign out"));
        signOutButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));

        return PageFactory.initElements(driver, SignInPage.class);
    }

    public static boolean DoesInboxLinkExist(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));

        return driver.findElement(By.partialLinkText("Inbox")).isDisplayed();
    }
}
