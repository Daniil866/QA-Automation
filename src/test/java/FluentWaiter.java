package hw_5;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class FluentWaiter {

    public static void main(String[] args) {
        int secondProduct = 1;

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.olx.ua/uk/");

        FluentWait<WebDriver> fluentWaiter = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchFieldError.class);

        WebElement searchField = driver.findElement(By.name("search"));
        searchField.sendKeys("iPad");

        WebElement searchButton = driver.findElement(By.cssSelector("[class*='search-form__submit']"));
        searchButton.click();

        fluentWaiter.until(ExpectedConditions.numberOfElementsToBe(By.tagName("rz-product-tile"), 60));

        List<WebElement> iPadProducts = driver.findElements(By.tagName("rz-product-tile"));
        WebElement secondIPad = iPadProducts.get(secondProduct);
        String secondIPadName = secondIPad.findElement(By.cssSelector(".text-base")).getText();

        System.out.println(secondIPadName);

        WebElement secondIPadBuyButton = secondIPad.findElement(By.cssSelector(".buy-button"));
        secondIPadBuyButton.click();

        fluentWaiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='header-cart__button'] .badge")));

        WebElement cartButton = driver.findElement(By.cssSelector("[class='header-cart__button']"));
        cartButton.click();

        fluentWaiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='cart-product__title']")));

        WebElement productNameFromCartElement = driver.findElement(By.cssSelector("[class='cart-product__title']"));
        String productNameFromCart = productNameFromCartElement.getText();

        System.out.println(productNameFromCart);

        Assert.assertEquals(secondIPadName, productNameFromCart);

        driver.quit();
    }

}