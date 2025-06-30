package com.samsung.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SamsungBuyButtonPresenceTest {

    private static final String SAMSUNG_BASE_URL = "https://www.samsung.com/ua/";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = null;
        try {
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            System.out.println("--- Starting Test: Verify 'Buy' Button Presence (Samsung) ---");
            testSamsungBuyButtonPresence(driver);

        } catch (Exception e) {
            System.err.println("An error occurred during test execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("--- Browser closed ---");
            }
        }
    }

    public static void testSamsungBuyButtonPresence(WebDriver driver) {
        System.out.println("==============================================");
        System.out.println("Test: Verify 'Buy' Button Presence (Samsung)");
        // Navigate to a specific product page, e.g., Galaxy S24 Ultra
        driver.get(SAMSUNG_BASE_URL + "smartphones/galaxy-s24-ultra-titanium-yellow-256gb-sm-s928bzydsek/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Accept cookies if the pop-up appears
            try {
                WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("c-shell-cookie-layer-btn")));
                acceptCookiesButton.click();
                System.out.println("Accepted cookies.");
            } catch (Exception e) {
                System.out.println("No cookie consent pop-up found or already handled.");
            }

            // Step 1: Find the "Купити" (Buy) or "Додати в кошик" (Add to cart) button
            // Locators for Samsung's buy button are often specific.
            // Let's try common patterns like "add-to-cart-button" or text-based.
            WebElement buyButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("button.cta-button.is-type-buy") // Common CSS for buy buttons on Samsung
            ));
            System.out.println("'Buy' button found.");

            // Step 2: Verify that the button is displayed and enabled
            boolean isDisplayed = buyButton.isDisplayed();
            boolean isEnabled = buyButton.isEnabled();

            if (isDisplayed) {
                System.out.println("'Buy' button is displayed on the page.");
            } else {
                System.err.println("'Buy' button is NOT displayed!");
            }

            if (isEnabled) {
                System.out.println("'Buy' button is enabled (clickable).");
            } else {
                System.err.println("'Buy' button is NOT enabled!");
            }

            if (isDisplayed && isEnabled) {
                System.out.println("Test PASSED: 'Buy' button is present and active.");
            } else {
                System.err.println("Test FAILED: 'Buy' button does not meet expectations (not displayed or not enabled).");
            }

        } catch (Exception e) {
            System.err.println("Test FAILED due to an exception: 'Buy' button not found or another error occurred.");
            e.printStackTrace();
        }
        System.out.println("==============================================");
    }
}