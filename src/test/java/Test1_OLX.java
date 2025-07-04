package hw_2;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static java.lang.Thread.sleep;

public class Test1_OLX {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            String keyWordToFind = "iPad";

            driver.manage().window().maximize();
            driver.get("https://www.olx.ua/");

            WebElement searchField = driver.findElement(By.cssSelector("#headerSearch"));
            searchField.sendKeys(keyWordToFind);
            searchField.submit();

            sleep(5000);

            WebElement iPadPageTitle = driver.findElement(By.cssSelector("h1[data-testid='listing-grid-title']"));
            String titleText = iPadPageTitle.getText();

            Assert.assertTrue("Title doesn't contain " + keyWordToFind + " word.", titleText.contains(keyWordToFind));

        } catch (AssertionError ex) {
            System.err.println("Test failed: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}