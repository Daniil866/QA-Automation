package hw_5;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ExplicitWaiter_example_3 {

    public static void main(String[] args) {
        int expectedCountOfProducts = 60;

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.olx.ua/uk/");

        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement searchField = driver.findElement(By.name("search"));
        searchField.sendKeys("iPhone");

        WebElement searchButton = driver.findElement(By.cssSelector("[class*='search-form__submit']"));
        searchButton.click();

        waiter.until(ExpectedConditions.numberOfElementsToBe(By.tagName("rz-product-tile"), expectedCountOfProducts));

        List<WebElement> productListElements = driver.findElements(By.tagName("rz-product-tile"));

        System.out.println("Count of elements: " + productListElements.size());

        Assert.assertEquals(expectedCountOfProducts, productListElements.size());

        driver.quit();
    }

}