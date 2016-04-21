import com.appsenseca.categories.Critical;
import com.appsenseca.categories.Major;
import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.EmailViewPage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.utils.WebUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

/**
 * Created by SunilM on 19/04/2016.
 */
public class GmailTest {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setDriver(){

        String browserName = System.getenv("browser");
        if(browserName != null && browserName.equalsIgnoreCase("Chrome")){
            String chromeDriver = GmailTest.class.getClassLoader().getResource("bin/chromedriver.exe").getPath();
            System.setProperty("webdriver.chrome.driver", chromeDriver);
            driver = new ChromeDriver();
        }else{
            driver = new FirefoxDriver();
        }
    }

    @Category({Critical.class})
    @Test
    public void gmailLoginShouldBeSuccessful(){

        SignInPage signInPage = WebUtil.goToSignInPage(driver);
        signInPage.fillInUserName(driver, "q20105@gmail.com");
        signInPage.fillInPassword(driver, "1211H4ck3d");
        EmailHomePage emailHomePage = signInPage.clickSignInButton(driver);

        Assert.assertTrue("Inbox link should be displayed",EmailHomePage.doesInboxLinkExist(driver));

        emailHomePage.SignOut(driver);

        Assert.assertTrue("Sign In button should be displayed",SignInPage.doesSignInButtonExist(driver));
    }

    @Category({Major.class})
    @Test
    public void gmailSendAndReceiveEmailShouldBeSuccessful(){

        SignInPage signInPage = WebUtil.goToSignInPage(driver);
        signInPage.fillInUserName(driver, "q20105@gmail.com");
        signInPage.fillInPassword(driver, "1211H4ck3d");
        EmailHomePage emailHomePage = signInPage.clickSignInButton(driver);
        emailHomePage.clickComposeEmailButton(driver);
        emailHomePage.fillInToField(driver, "q20105@gmail.com");
        final String actualSubject = "This is a test subject";
        emailHomePage.fillInSubjectField(driver, actualSubject);
        emailHomePage.fillInDescriptionField(driver, "This is a test subject");
        emailHomePage.clickSendButton(driver);

        EmailViewPage emailViewPage = emailHomePage.clickEmail(driver);

        String actualNewEmailSubjectText = emailViewPage.getNewEmailSubjectText(driver);

        Assert.assertEquals("Subjects should be the same", actualNewEmailSubjectText, actualSubject);
    }

    @After
    public void tearDown(){

        driver.quit();
    }
}
