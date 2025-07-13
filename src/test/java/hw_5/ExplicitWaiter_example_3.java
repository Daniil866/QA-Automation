package hw_5;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ExplicitWaiter_example_3 {

    public static void main(String[] args) {
        int expectedCountOfProducts = 30;

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.olx.ua/uk/");

        FluentWait<WebDriver> fluentWaiter = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchFieldError.class);

        WebElement searchField = driver.findElement(new By.ById("search"));
        searchField.sendKeys("iPhone");

        WebElement searchButton = driver.findElement(By.name("searchBtn"));
        searchButton.click();

        fluentWaiter.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[data-cy=\"l-card\"]"), expectedCountOfProducts));

        List<WebElement> productListElements = driver.findElements(By.cssSelector("[data-cy=\"l-card\"]"));

        System.out.println("Count of elements: " + productListElements.size());

        Assert.assertTrue(expectedCountOfProducts <= productListElements.size());
        //Assert.assertEquals(expectedCountOfProducts, productListElements.size());

        driver.quit();
    }

}