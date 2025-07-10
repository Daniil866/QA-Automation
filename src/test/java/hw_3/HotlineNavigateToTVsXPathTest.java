package HW_3;

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://hotline.ua/");
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
            WebElement catalogMenuButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'header__catalog-btn')]")));
            catalogMenuButton.click();

            WebElement tvsCategoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class, 'catalog-navigation__item')]//a[contains(text(), 'Телевізори')]")));
            tvsCategoryLink.click();

            WebElement categoryTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class, 'page__title') and contains(text(), 'Телевізори')]")));
            String titleText = categoryTitle.getText();

            Assert.assertTrue("Заголовок сторінки не 'Телевізори'", titleText.contains("Телевізори"));
            System.out.println("Test 'navigateToTVsCategoryXPath' passed. Found: " + titleText);
        } catch (AssertionError e) {
            System.err.println("Test 'navigateToTVsCategoryXPath' failed: " + e.getMessage());
            throw e;
        }
    }
}