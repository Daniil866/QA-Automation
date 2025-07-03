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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HotlineChangeCurrencyTest {

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
    void testChangeCurrencyToUSD() {
        // Step 1: Find the currency dropdown/selector
        // This locator might need adjustment based on current site structure
        WebElement currencyDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".user-link.currency .value")));
        currencyDropdown.click();
        System.out.println("Clicked currency dropdown.");

        // Step 2: Select USD from the dropdown options
        WebElement usdOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'user-currency')]//a[normalize-space(text())='USD']")));
        usdOption.click();
        System.out.println("Selected 'USD' currency option.");

        // Step 3: Wait for the currency to update and verify the symbol on a price element
        // Navigate to a product page or category page where prices are displayed
        driver.get("https://hotline.ua/computer/noutbuki-netbuki/");
        System.out.println("Navigated to Laptops page to check currency.");

        WebElement firstProductPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".price-info strong")));
        String priceText = firstProductPrice.getText();
        System.out.println("First product price: " + priceText);

        assertTrue(priceText.contains("$") || priceText.contains("USD"),
                "Product price should contain '$' or 'USD' after currency change.");
        System.out.println("Verified currency symbol is '$' or 'USD'.");
    }
}