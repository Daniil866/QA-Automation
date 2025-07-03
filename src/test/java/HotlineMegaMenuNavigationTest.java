import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions; // Для наведения курсора
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HotlineMegaMenuNavigationTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions; // Для действий с мышью

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver); // Инициализация Actions

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
    void testMegaMenuNavigationToMonitors() {
        // Step 1: Click the "Каталог товаров" (Product Catalog / Burger menu) button
        WebElement catalogButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".menu-opener")));
        catalogButton.click();
        System.out.println("Clicked 'Product Catalog' button.");

        // Step 2: Wait for the mega menu to be visible
        WebElement megaMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sidebar-menu")));
        assertTrue(megaMenu.isDisplayed(), "Mega menu should be visible.");
        System.out.println("Mega menu is visible.");

        // Step 3: Hover over "Компьютеры" (Computers) main category
        // Find the main category link in the menu
        WebElement computersCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'sidebar-menu')]//a[contains(@href, '/computer/') and contains(text(), 'Компьютеры')]")
        ));
        actions.moveToElement(computersCategory).perform(); // Наведение курсора
        System.out.println("Hovered over 'Computers' category.");

        // Step 4: Click on "Мониторы" (Monitors) sub-category from the dropdown
        WebElement monitorsSubcategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'sidebar-menu-list')]//a[contains(@href, '/computer/monitory/') and contains(text(), 'Мониторы')]")
                // This XPath assumes the sub-menu items appear within a div like 'sidebar-menu-list'
        ));
        monitorsSubcategory.click();
        System.out.println("Clicked 'Monitors' sub-category.");

        // Step 5: Assert that the correct "Мониторы" page is loaded
        wait.until(ExpectedConditions.urlContains("/computer/monitory/"));
        WebElement pageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        assertTrue(pageHeader.getText().contains("Мониторы") || pageHeader.getText().contains("Monitors"),
                "Page header should contain 'Мониторы' or 'Monitors'.");
        System.out.println("Navigated to Monitors page. Header: " + pageHeader.getText());
    }
}