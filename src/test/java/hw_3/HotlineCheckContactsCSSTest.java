package hw_3;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HotlineCheckContactsCSSTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rozetka.com.ua/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkFooterContactLinkCSS() {
        try {
            WebElement contactsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/contacts/']")));
            contactsLink.click();

            WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.pages__h1")));
            String titleText = pageTitle.getText();

            Assert.assertTrue("Заголовок сторінки не 'Контакти'", titleText.contains("Контакти"));
            System.out.println("Test 'checkFooterContactLinkCSS' passed. Found: " + titleText);
        } catch (AssertionError e) {
            System.err.println("Test 'checkFooterContactLinkCSS' failed: " + e.getMessage());
            throw e;
        }
    }
}