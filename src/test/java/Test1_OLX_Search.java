package hw_3;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static java.lang.Thread.sleep;

public class Test1_OLX_Search {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            String keyWordToFind = "ноутбук";

            driver.manage().window().maximize();
            driver.get("https://www.olx.ua/uk/");

            WebElement searchField = driver.findElement(By.cssSelector("#headerSearch"));
            searchField.sendKeys(keyWordToFind, Keys.ENTER);

            sleep(4000);

            WebElement pageTitle = driver.findElement(By.cssSelector("h1[data-testid='listing-grid-title']"));
            String titleText = pageTitle.getText();

            Assert.assertTrue("Title doesn't contain " + keyWordToFind + " word.", titleText.contains(keyWordToFind));

        } catch (AssertionError ex) {
            System.err.println("Test failed: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}