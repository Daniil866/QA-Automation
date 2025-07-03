import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HotlineCompareProductsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://hotline.ua/");
        System.out.println("Navigated to hotline.ua");

        // --- Handle Cookie Consent Pop-up ---
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("accept-cookie")));
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
                System.out.println("Successfully clicked 'Accept cookies' button.");
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("accept-cookie")));
                System.out.println("Cookie banner disappeared.");
            }
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Cookie consent banner did not appear or was not clickable within timeout. Continuing test.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while handling cookie consent: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAddProductsToComparison() {
        // Step 1: Navigate to a category page, e.g., Televisions
        driver.get("https://hotline.ua/av-televizory/");
        System.out.println("Navigated to Televisions category page.");

        // Step 2: Find and add the first product to comparison
        WebElement firstProductCompareButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(@class, 'product-item')]//i[contains(@class, 'icon-compare-add')])[1]")
                // Finds the first 'Add to Compare' icon/button within a product item
        ));
        String firstProductName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[contains(@class, 'product-item')]//a[contains(@class, 'product-item__title')])[1]")
        )).getText();
        firstProductCompareButton.click();
        System.out.println("Added first product '" + firstProductName + "' to comparison.");

        // Step 3: Find and add the second product to comparison
        WebElement secondProductCompareButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(@class, 'product-item')]//i[contains(@class, 'icon-compare-add')])[2]")
        ));
        String secondProductName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[contains(@class, 'product-item')]//a[contains(@class, 'product-item__title')])[2]")
        )).getText();
        secondProductCompareButton.click();
        System.out.println("Added second product '" + secondProductName + "' to comparison.");

        // Step 4: Click the "Comparison" button/link (usually in header or floating bar)
        // This might appear after adding items to comparison.
        WebElement compareIconInHeader = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header-bottom .compare")));
        compareIconInHeader.click();
        System.out.println("Clicked comparison icon in header.");

        // Step 5: Assert that both products are on the comparison page
        wait.until(ExpectedConditions.urlContains("/compare/"));
        System.out.println("Navigated to comparison page: " + driver.getCurrentUrl());

        WebElement product1OnCompare = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Сравнение')]/ancestor::div[@class='compare']//a[contains(text(), '" + firstProductName.substring(0, Math.min(firstProductName.length(), 20)) + "')]")));
        WebElement product2OnCompare = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Сравнение')]/ancestor::div[@class='compare']//a[contains(text(), '" + secondProductName.substring(0, Math.min(secondProductName.length(), 20)) + "')]")));

        assertTrue(product1OnCompare.isDisplayed(), "First product should be displayed on comparison page.");
        assertTrue(product2OnCompare.isDisplayed(), "Second product should be displayed on comparison page.");
        System.out.println("Both products verified on comparison page.");
    }
}