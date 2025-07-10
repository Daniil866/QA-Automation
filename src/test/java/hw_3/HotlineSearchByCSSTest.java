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

public class HotlineSearchByCSSTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://hotline.ua/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void searchForHPProBookCSS() {
        String keyWordToFind = "HP ProBook 450 G10";
        try {
            WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#search")));
            searchField.sendKeys(keyWordToFind);

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.search__btn")));
            searchButton.click();

            WebElement searchResultsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.search-list__title")));
            String titleText = searchResultsTitle.getText();

            Assert.assertTrue("Заголовок не містить '" + keyWordToFind + "'", titleText.contains(keyWordToFind));
            System.out.println("Test 'searchForHPProBookCSS' passed. Found: " + titleText);
        } catch (AssertionError e) {
            System.err.println("Test 'searchForHPProBookCSS' failed: " + e.getMessage());
            throw e;
        }
    }
}