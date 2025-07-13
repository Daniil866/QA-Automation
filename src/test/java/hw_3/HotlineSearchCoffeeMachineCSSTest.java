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

public class HotlineSearchCoffeeMachineCSSTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rozetka.com.ua/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void searchForCoffeeMachineCSS() {
        String keyWordToFind = "Кавомашина DeLonghi";
        try {
            WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='search']")));
            searchField.sendKeys(keyWordToFind);

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".search-form__submit")));
            searchButton.click();

            WebElement searchResultsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-heading")));
            String titleText = searchResultsTitle.getText();

            Assert.assertTrue("Заголовок не містить '" + keyWordToFind + "'", titleText.contains(keyWordToFind));
            System.out.println("Test 'searchForCoffeeMachineCSS' passed. Found: " + titleText);
        } catch (AssertionError e) {
            System.err.println("Test 'searchForCoffeeMachineCSS' failed: " + e.getMessage());
            throw e;
        }
    }
}