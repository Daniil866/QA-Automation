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

public class HotlineAddToFavoritesTest {

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
    void testAddProductToFavorites() {
        // Step 1: Navigate to a specific product page
        // Using a direct URL to a popular product for consistency
        driver.get("https://hotline.ua/computer-noutbuki/apple-macbook-air-13-m3-2024-mla03/");
        System.out.println("Navigated to product page.");

        // Get product name for verification
        WebElement productNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product__heading")));
        String productName = productNameElement.getText();
        System.out.println("Product name: " + productName);

        // Step 2: Click the "Add to Favourites" button
        // Locator for "Добавить в избранное" / "В избранное" button/icon
        WebElement addToFavoritesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'item-fav') or contains(@class, 'favorites-link')]")));
        addToFavoritesButton.click();
        System.out.println("Clicked 'Add to Favourites' button.");

        // Step 3: Wait for confirmation (if any) or navigate to favorites page
        // Wait for the favorite icon in the header to indicate a change (e.g., count increases)
        wait.until(ExpectedConditions.attributeContains(By.cssSelector(".header-bottom .favorites .value"), "data-count", "1"));
        System.out.println("Favorites count updated.");

        // Step 4: Click on the Favorites icon/link in the header
        WebElement favoritesLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header-bottom .favorites")));
        favoritesLink.click();
        System.out.println("Clicked Favorites link in header.");

        // Step 5: Assert that the product is visible on the Favorites page
        wait.until(ExpectedConditions.urlContains("/favorites/"));
        WebElement favoriteProductTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'product-item__title') and contains(text(), '" + productName.substring(0, Math.min(productName.length(), 20)) + "')]")));
        assertTrue(favoriteProductTitle.isDisplayed(), "Product should be displayed on the Favourites page.");
        System.out.println("Verified product '" + productName + "' on Favourites page.");
    }
}