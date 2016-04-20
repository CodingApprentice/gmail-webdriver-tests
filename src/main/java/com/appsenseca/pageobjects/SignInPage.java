package com.appsenseca.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by SunilM on 20/04/2016.
 */
public class SignInPage {

    WebDriverWait wait;

    public void fillInUserName(WebDriver driver, String userName) {

        WebElement emailTextBox = driver.findElement(By.id("Email"));
        emailTextBox.clear();
        emailTextBox.sendKeys(userName);

        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();
    }

    public void fillInPassword(WebDriver driver, String password) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.sendKeys(password);
    }

    public EmailHomePage clickSignInButton(WebDriver driver) {

        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        return PageFactory.initElements(driver, EmailHomePage.class);
    }

    public static boolean doesSignInButtonExist(WebDriver driver) {

        return driver.findElement(By.id("signIn")).isDisplayed();
    }
}
