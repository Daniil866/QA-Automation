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

public class HotlineNavigateToTVsXPathTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("https://rozetka.com.ua/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void navigateToTVsCategoryXPath() {
        try {
            WebElement catalogMenuButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@data-testid, 'fat_menu_btn')]")));
            catalogMenuButton.click();

            WebElement tvsCategoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[3]/a[contains(text(), 'Процесори')]")));
            tvsCategoryLink.click();

            WebElement categoryTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class, 'catalog-heading') and contains(text(), 'Процесори')]")));
            String titleText = categoryTitle.getText();

            Assert.assertTrue("Заголовок сторінки не 'Процесори'", titleText.contains("Процесори"));
            System.out.println("Test 'navigateToTVsCategoryXPath' passed. Found: " + titleText);
        } catch (AssertionError e) {
            System.err.println("Test 'navigateToTVsCategoryXPath' failed: " + e.getMessage());
            throw e;
        }
    }
}