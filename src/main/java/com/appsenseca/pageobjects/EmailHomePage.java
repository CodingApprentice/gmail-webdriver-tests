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

    public static boolean doesInboxLinkExist(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));

        return driver.findElement(By.partialLinkText("Inbox")).isDisplayed();
    }

    public static void clickComposeEmailButton(WebDriver driver) {

        WebDriverWait wait;
        wait = new WebDriverWait(driver, 30);
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));

        WebElement composeEmailButton = driver.findElement(By.xpath(".//*[@id=':it']/div/div"));
        composeEmailButton.click();
    }

    public static void fillInToField(WebDriver driver, String toText) {

        WebDriverWait wait;
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name='to']")));
        WebElement toInputTextBox = driver.findElement(By.cssSelector("textarea[name='to']"));
        toInputTextBox.clear();
        toInputTextBox.sendKeys(toText);
    }

    public static void fillInSubjectField(WebDriver driver, String subjectText) {

        WebElement subjectboxInputTextBox = driver.findElement(By.cssSelector("input[name='subjectbox']"));
        subjectboxInputTextBox.clear();
        subjectboxInputTextBox.sendKeys(subjectText);
    }

    public static void fillInDescriptionField(WebDriver driver, String bodyText) {

        WebElement descriptionInputTextBox = driver.findElement(By.cssSelector("div[aria-label=\"Message Body\"]"));
        descriptionInputTextBox.clear();
        final String body = bodyText;
        descriptionInputTextBox.sendKeys(body);
    }

    public static void clickSendButton(WebDriver driver) {

        WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*=\"Send\"]"));
        sendButton.click();
    }

    public EmailViewPage clickEmail(WebDriver driver) {

        WebElement inboxLinkText = driver.findElement(By.partialLinkText("Inbox"));
        inboxLinkText.click();

        WebDriverWait wait;
        wait = new WebDriverWait(driver, 60);
        final String emailCssLocator = "div[class='y6'] span[id] b";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailCssLocator)));
        WebElement newEmail = driver.findElement(By.cssSelector(emailCssLocator));
        newEmail.click();

        return PageFactory.initElements(driver, EmailViewPage.class);
    }
}
