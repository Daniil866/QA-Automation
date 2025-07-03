import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HotlineSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Selenium Manager will automatically download and set up ChromeDriver.
        // No need for WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        // options.addArguments("--headless"); // Uncomment to run tests without a UI

        driver = new ChromeDriver(options); // Selenium Manager finds the driver automatically

        // Configure implicit and explicit waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Navigate to the main page and wait for it to load
        driver.get("https://hotline.ua/");
        wait.until(ExpectedConditions.urlContains("hotline.ua"));
        System.out.println("Navigated to Hotline.ua main page.");
    }

    @Test
    public void searchForProduct_displaysCorrectResults() {
        // Updated search term to the specific Samsung Galaxy S25 Ultra model
        String searchTerm = "Samsung Galaxy S25 Ultra 12/256GB Titanium Black (SM-S938BZKD)";

        // 1. Find the search input field
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("search")));
        System.out.println("Search input field found and clickable.");

        // 2. Enter the search query
        searchInput.sendKeys(searchTerm);
        System.out.println("Entered search term: '" + searchTerm + "'");

        // 3. Click the search button or press Enter
        try {
            // Hotline uses a magnifying glass icon as the search button
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.search-button.icon-search")));
            searchButton.click();
            System.out.println("Search button clicked.");
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            // If the button is not found or clickable, try submitting the form with Enter
            searchInput.submit();
            System.out.println("Search submitted by pressing Enter (search button not found or clickable).");
        }

        // 4. ASSERTIONS: Verify search results

        // ASSERT 1: Check URL for the search query
        String expectedUrlPart = searchTerm.replace(" ", "+").toLowerCase();
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.toLowerCase().contains(expectedUrlPart),
                "Search results page URL should contain '" + expectedUrlPart + "'. Actual URL: " + currentUrl);
        System.out.println("URL verified: " + currentUrl);

        // ASSERT 2: Check for the presence of elements containing the product name on the results page
        try {
            // For such a specific model, it's best to look for elements containing the core part of the name.
            // "Galaxy S25 Ultra" is a good partial term to look for in results.
            String partialSearchTerm = "Galaxy S25 Ultra";
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + partialSearchTerm.toLowerCase() + "')]")));

            Assert.assertTrue(resultElement.isDisplayed(),
                    "Search results page should display an element containing '" + partialSearchTerm + "'.");
            System.out.println("Element '" + partialSearchTerm + "' found on the results page.");

            // Optional: More precise assertion if needed, depending on actual search result page structure.
            // For instance, looking for the full model text in a specific element:
            // WebElement exactModelTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            //     By.xpath("//a[contains(text(), 'Samsung Galaxy S25 Ultra 12/256GB Titanium Black (SM-S938BZKD)')]")));
            // Assert.assertTrue(exactModelTextElement.isDisplayed(), "Exact model text found on the page.");

        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("Failed to find an element confirming search results for '" + searchTerm + "'. " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser
            System.out.println("Browser closed.");
        }
    }
}