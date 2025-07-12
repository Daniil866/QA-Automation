package hw_5;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaiter_example_1 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.olx.ua/uk/");

        WebElement searchField = driver.findElement(new By.ById("search"));
        searchField.sendKeys("iPhone");

        WebElement searchButton = driver.findElement(By.name("searchBtn"));
        searchButton.click();

        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(3));
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=\"listing-grid\"]")));

        WebElement titleElement = driver.findElement(By.cssSelector("[data-testid=\"listing-grid\"]"));

        Assert.assertTrue(titleElement.isDisplayed());

        driver.quit();
    }
}