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

public class HotlineCheckLogoXPathTest {

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
    public void checkHotlineLogoXPath() {
        try {
            WebElement hotlineLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'header__logo')]")));
            Assert.assertTrue("Логотип Hotline не відображається", hotlineLogo.isDisplayed());
            System.out.println("Test 'checkHotlineLogoXPath' passed. Hotline logo is displayed.");
        } catch (AssertionError e) {
            System.err.println("Test 'checkHotlineLogoXPath' failed: " + e.getMessage());
            throw e;
        }
    }
}