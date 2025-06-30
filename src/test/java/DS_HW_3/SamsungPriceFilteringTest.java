package com.samsung.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions; // For complex interactions like sliders
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SamsungPriceFilteringTest {

    private static final String SAMSUNG_BASE_URL = "https://www.samsung.com/ua/"; // Ukrainian Samsung site

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = null;
        try {
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            System.out.println("--- Starting Test: Samsung Product Price Filtering ---");
            testSamsungPriceFiltering(driver);

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

    public static void testSamsungPriceFiltering(WebDriver driver) {
        System.out.println("==============================================");
        System.out.println("Test: Samsung Product Price Filtering");
        // Navigate to a product category, e.g., Smartphones
        driver.get(SAMSUNG_BASE_URL + "smartphones/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Accept cookies if the pop-up appears
            try {
                WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("c-shell-cookie-layer-btn")));
                acceptCookiesButton.click();
                System.out.println("Accepted cookies.");
            } catch (Exception e) {
                System.out.println("No cookie consent pop-up found or already handled.");
            }

            // --- Price Filtering Logic for Samsung Site ---
            // Samsung's filtering is often more complex, using sliders or predefined ranges.
            // We'll try to find a price range filter.
            // Example: Navigating to a specific product type page that has clear filters
            driver.get(SAMSUNG_BASE_URL + "smartphones/all-smartphones/?page=1");

            // Wait for the filter section to be visible
            WebElement filterContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".filter-search-by__inner")));
            System.out.println("Filter container found.");

            // Attempt to click on a price filter or open a price range section
            // This locator might vary. Samsung often uses dropdowns or sections for filters.
            // We'll try to find a general "Price" filter header and click it if it's collapsible.
            // Or, if there are direct min/max inputs, we'll use those.

            // Let's assume a common structure where price filters are under an accordion
            // Finding a price range slider (often complex to automate reliably via direct value input)
            // Instead, let's try to select a predefined price range or sort by price, as direct input fields are rare on Samsung's filter.
            // For a more robust solution, you would need to identify the exact price filter elements.

            // Example: Find and click a "Price" filter button/header if it exists.
            // Locators for Samsung filters are often dynamic. Let's try a general approach:
            List<WebElement> filterHeaders = driver.findElements(By.cssSelector(".filter-attribute__title-wrap"));
            WebElement priceFilterHeader = null;
            for (WebElement header : filterHeaders) {
                if (header.getText().contains("Ціна") || header.getText().contains("Price")) { // Adjust text based on site language
                    priceFilterHeader = header;
                    break;
                }
            }

            if (priceFilterHeader != null) {
                System.out.println("Found 'Price' filter header. Clicking it if it's collapsible.");
                try {
                    priceFilterHeader.click(); // May expand a price filter section
                    Thread.sleep(1000); // Small pause for UI to update
                } catch (Exception e) {
                    System.out.println("Price filter header not clickable or already open.");
                }
            } else {
                System.out.println("Could not find a general 'Price' filter header. Trying direct price ranges if visible.");
            }

            // Samsung site usually uses checkboxes for price ranges.
            // Let's try to select a specific price range, e.g., "From 20 000 UAH"
            // The exact text/value for these options might change.
            String minPriceRangeText = "20 000"; // This text must exactly match on the website.
            String maxPriceRangeText = "30 000"; // Example range

            // Locators for price range checkboxes/radio buttons are often specific
            // Trying a common pattern: filter-attribute-option__name for the label text
            List<WebElement> priceRangeOptions = driver.findElements(By.cssSelector(".filter-attribute-option__label"));
            WebElement selectedMinPriceRange = null;
            WebElement selectedMaxPriceRange = null;

            for (WebElement option : priceRangeOptions) {
                if (option.getText().contains(minPriceRangeText)) {
                    selectedMinPriceRange = option;
                    break;
                }
            }

            if (selectedMinPriceRange != null) {
                selectedMinPriceRange.click();
                System.out.println("Selected minimum price range: " + minPriceRangeText);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-overlay"))); // Wait for loading
                Thread.sleep(2000); // Give time for results to load
            } else {
                System.err.println("Could not find price range option: " + minPriceRangeText);
            }

            // If there's another checkbox for a higher range, select it to narrow down
            // This is an example, Samsung's filtering might require only one selection per filter
            for (WebElement option : priceRangeOptions) {
                if (option.getText().contains(maxPriceRangeText)) { // Check for options like "до 30 000" or similar
                    selectedMaxPriceRange = option;
                    break;
                }
            }

            if (selectedMaxPriceRange != null && selectedMaxPriceRange != selectedMinPriceRange) {
                selectedMaxPriceRange.click(); // Select a second range if it refines
                System.out.println("Selected maximum price range: " + maxPriceRangeText);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-overlay")));
                Thread.sleep(2000);
            } else {
                System.out.println("No distinct upper price range option found or needed.");
            }


            // Step 5: Verification: Get prices of the first few products and check if they are within the range
            List<WebElement> productPrices = driver.findElements(By.cssSelector(".product-card__price-current")); // Adjust for Samsung's price locator
            boolean allPricesInExpectedRange = true;
            int checkedCount = 0;
            System.out.println("Checking prices of the first products:");

            if (productPrices.isEmpty()) {
                System.err.println("Test FAILED: No products found after filtering.");
                allPricesInExpectedRange = false;
            } else {
                for (WebElement priceElement : productPrices) {
                    if (checkedCount >= 5) break;

                    String priceText = priceElement.getText().replaceAll("[^0-9]", ""); // Remove non-numeric characters
                    if (priceText.isEmpty()) { // Handle cases where price might not be immediately visible or formatted
                        System.out.println("  Skipping empty price element.");
                        continue;
                    }
                    int price = Integer.parseInt(priceText);

                    // Assuming the filtering actually applied a range
                    // This verification is highly dependent on the actual filtering mechanism of Samsung
                    // For a simple range like "from 20000", we just check >= 20000
                    if (price < 20000) { // If we only selected "from 20000"
                        System.err.println("  Product price " + price + " is NOT within the expected range (e.g., >= 20000)");
                        allPricesInExpectedRange = false;
                    } else {
                        System.out.println("  Price: " + price + " - is within range.");
                    }
                    checkedCount++;
                }
            }

            if (allPricesInExpectedRange && !productPrices.isEmpty()) {
                System.out.println("Test PASSED: All checked products are within the specified price range.");
            } else {
                System.err.println("Test FAILED: Some products are not within the specified price range or no products found.");
            }

        } catch (Exception e) {
            System.err.println("Test FAILED due to an exception: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("==============================================");
    }
}