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

public class HotlineFilterByManufacturerTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Увеличенный таймаут для всех ожиданий

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
    void testFilterLaptopsByLenovo() {
        // Step 1: Navigate directly to the Laptops category page
        driver.get("https://hotline.ua/computer/noutbuki-netbuki/");
        System.out.println("Navigated to Laptops category page.");

        // Step 2: Scroll to the manufacturer filter and click on "Lenovo"
        // Find the filter section first to ensure visibility
        WebElement manufacturerFilterHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space(text())='Производитель']")));
        // Scroll into view if necessary (though ExpectedConditions.elementToBeClickable often handles this)
        // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", manufacturerFilterHeader);

        WebElement lenovoCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='item-body' and preceding-sibling::h4[normalize-space(text())='Производитель']]//a[contains(text(), 'Lenovo')]")
                // This XPath looks for a link with text 'Lenovo' within the filter body
                // that is a sibling of the 'Производитель' header.
        ));
        lenovoCheckbox.click();
        System.out.println("Clicked 'Lenovo' manufacturer filter.");

        // Step 3: Wait for the filter to apply (e.g., price range or product list updates)
        // A common way to wait for filters is to wait for a loading spinner to disappear
        // or for the product count to update. Here, we'll wait for the URL to change.
        wait.until(ExpectedConditions.urlContains("producer=lenovo"));
        System.out.println("URL updated to include 'lenovo' filter.");

        // Step 4: Assert that the page title or a product name indicates "Lenovo"
        WebElement firstProductTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item__title a")));
        assertTrue(firstProductTitle.getText().contains("Lenovo"),
                "First product title should contain 'Lenovo' after filtering.");
        System.out.println("Verified first product title contains 'Lenovo': " + firstProductTitle.getText());
    }
}