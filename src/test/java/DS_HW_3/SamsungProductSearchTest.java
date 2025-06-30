package com.samsung.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys; // For pressing Enter key
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SamsungProductSearchTest {

    private static final String SAMSUNG_BASE_URL = "https://www.samsung.com/ua/";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = null;
        try {
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            System.out.println("--- Starting Test: Verify Product Search (Samsung) ---");
            testSamsungProductSearch(driver);

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

    public static void testSamsungProductSearch(WebDriver driver) {
        System.out.println("==============================================");
        System.out.println("Test: Verify Product Search (Samsung)");
        driver.get(SAMSUNG_BASE_URL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased timeout for initial page load

        try {
            // Accept cookies if the pop-up appears
            try {
                WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("c-shell-cookie-layer-btn")));
                acceptCookiesButton.click();
                System.out.println("Accepted cookies.");
            } catch (Exception e) {
                System.out.println("No cookie consent pop-up found or already handled.");
            }

            // Step 1: Open the search bar (Samsung often has a search icon to click first)
            // Locate and click the search icon or button to reveal the input field
            WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.global-search__search-btn") // Common Samsung search icon locator
            ));
            searchIcon.click();
            System.out.println("Clicked search icon.");

            // Step 2: Find the search input field and enter a query
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input#gnb-search-keyword") // Common Samsung search input field locator
            ));
            String searchQuery = "Galaxy S24";
            searchInput.sendKeys(searchQuery);
            System.out.println("Entered search query: '" + searchQuery + "'");

            searchInput.sendKeys(Keys.ENTER); // Press Enter to initiate search
            System.out.println("Pressed Enter to search.");

            // Step 3: Wait for search results to load
            // Wait for URL to change to search results page
            wait.until(ExpectedConditions.urlContains("/search/"));
            // Wait for the presence of product cards or search result titles
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".result-product__wrap")));
            System.out.println("Search results loaded.");

            // Step 4: Verification: Check if search results are displayed and relevant
            List<WebElement> productTitles = driver.findElements(By.cssSelector(".result-product__name")); // Locator for product titles on search results
            List<WebElement> noResults = driver.findElements(By.cssSelector(".search-no-result")); // Check for no results message

            if (!noResults.isEmpty() && noResults.get(0).isDisplayed()) {
                System.err.println("Test FAILED: Search for '" + searchQuery + "' returned no results.");
            }
            else if (!productTitles.isEmpty()) {
                System.out.println("Test PASSED: Search returned results.");
                String firstProductTitle = productTitles.get(0).getText();
                System.out.println("First result: " + firstProductTitle);

                // Additional check: Does the first product title contain the search query?
                if (firstProductTitle.toLowerCase().contains(searchQuery.toLowerCase())) {
                    System.out.println("Test (Relevance) PASSED: First result is relevant to the search query.");
                } else {
                    System.err.println("Test (Relevance) FAILED: First result title does not contain the search query.");
                }

            } else {
                System.err.println("Test FAILED: No product titles found after search, but no 'no results' message either (unexpected).");
            }

        } catch (Exception e) {
            System.err.println("Test FAILED due to an exception: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("==============================================");
    }
}