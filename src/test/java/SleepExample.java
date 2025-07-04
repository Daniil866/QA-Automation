package hw_5;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class SleepExample {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.olx.ua/uk/");

        WebElement searchField = driver.findElement(By.name("search"));
        searchField.sendKeys("iPhone");

        WebElement searchButton = driver.findElement(By.cssSelector("[class*='search-form__submit']"));
        searchButton.click();

        sleep(3000);

        WebElement titleElement = driver.findElement(By.cssSelector(".catalog-heading"));

        Assert.assertTrue(titleElement.isDisplayed());

        driver.quit();
    }

}