import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.utils.WebUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by SunilM on 19/04/2016.
 */
public class GmailTests {

    WebDriver driver;
    WebDriverWait wait;

    @Test
    public void gmailLoginShouldBeSuccessful(){

        driver = new FirefoxDriver();

        SignInPage signInPage = WebUtil.goToSignInPage(driver);
        signInPage.fillInUserName(driver, "q20105@gmail.com");
        signInPage.fillInPassword(driver, "1211H4ck3d");
        EmailHomePage emailHomePage = signInPage.clickSignInButton(driver);

        Assert.assertTrue("Inbox link should be displayed",EmailHomePage.DoesInboxLinkExist(driver));

        emailHomePage.SignOut(driver);

        Assert.assertTrue("Sign In button should be displayed",SignInPage.DoesSignInButtonExist(driver));

    }

    @Test
    public void gmailSendAndReceiveEmailShouldBeSuccessful(){

        driver = new FirefoxDriver();

        driver.navigate().to("http://gmail.com");

        WebElement emailTextBox = driver.findElement(By.id("Email"));
        emailTextBox.sendKeys("q20105@gmail.com");

        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys("1211H4ck3d");
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));

        WebElement composeEmailButton = driver.findElement(By.xpath(".//*[@id=':it']/div/div"));
        composeEmailButton.click();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name='to']")));
        WebElement toInputTextBox = driver.findElement(By.cssSelector("textarea[name='to']"));
        toInputTextBox.clear();
        final String to = "q20105@gmail.com";
        toInputTextBox.sendKeys(to);

        WebElement subjectboxInputTextBox = driver.findElement(By.cssSelector("input[name='subjectbox']"));
        subjectboxInputTextBox.clear();
        final String subject = "This is a test subject";
        subjectboxInputTextBox.sendKeys(subject);

        WebElement descriptionInputTextBox = driver.findElement(By.cssSelector("div[aria-label=\"Message Body\"]"));
        descriptionInputTextBox.clear();
        final String body = "This is a test description";
        descriptionInputTextBox.sendKeys(body);

        WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*=\"Send\"]"));
        sendButton.click();

        WebElement inboxLinkText = driver.findElement(By.partialLinkText("Inbox"));
        inboxLinkText.click();

        wait = new WebDriverWait(driver, 30);
        final String emailCssLocator = "div[class='y6'] span[id] b";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailCssLocator)));
        WebElement newEmail = driver.findElement(By.cssSelector(emailCssLocator));
        newEmail.click();

        String newEmailSubjectText = driver.findElement(By.cssSelector("td div div div h2")).getText();

        Assert.assertEquals(newEmailSubjectText, subject);
    }

    @After
    public void tearDown(){

        driver.quit();
    }
}
