package hw_3;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        try {
            String keyWordToFind = "iPhone";

            driver.manage().window().maximize();
            driver.get("https://rozetka.com.ua/");

            WebElement searchField = driver.findElement(By.cssSelector("[name='search']"));
            searchField.sendKeys(keyWordToFind);

            WebElement searchButton = driver.findElement(By.cssSelector("[class*='search-form__submit']"));
            searchButton.click();

            sleep(3000);

            WebElement iPhonePageTitle = driver.findElement(By.cssSelector("[class='catalog-heading']"));
            String titleText = iPhonePageTitle.getText();

            Assert.assertTrue("Title doesn't contains " + keyWordToFind + " word.", titleText.contains(keyWordToFind));
        } catch (AssertionError ex) {
            ex.printStackTrace();
        } finally {
            driver.quit();
        }

    }

}
