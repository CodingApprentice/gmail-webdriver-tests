import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by SunilM on 19/04/2016.
 */
public class GmailSignInTest {

    WebDriver driver;
    WebDriverWait wait;

    @Test
    public void gmailLoginShouldBeSuccessful(){
        driver = new FirefoxDriver();

        driver.navigate().to("http://gmail.com");

        WebElement emailTextBox = driver.findElement(By.id("Email"));
        emailTextBox.sendKeys("q20105@gmail.com");

        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.sendKeys("1211H4ck3d");
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        WebElement inboxLinkText = driver.findElement(By.partialLinkText("Inbox"));
        Assert.assertTrue("Inbox link should be displayed",inboxLinkText.isDisplayed());
        //WebElement accountLogo = driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/a/span"));
        WebElement accountLogo = driver.findElement(By.cssSelector(".gb_1a.gbii"));
        accountLogo.click();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Sign out")));
        WebElement signOutButton = driver.findElement(By.partialLinkText("Sign out"));
        signOutButton.click();

        Assert.assertTrue("Sign In button should be displayed",driver.findElement(By.id("signIn")).isDisplayed());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
