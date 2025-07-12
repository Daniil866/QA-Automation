package hw_5;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class FluentWaiter {

    public static void main(String[] args) throws InterruptedException {
        int secondProduct = 2;

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://brain.com.ua/ukr/");

        FluentWait<WebDriver> fluentWaiter = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchFieldError.class);

        WebElement searchField = fluentWaiter.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.search-form.header-search-form input.quick-search-input[placeholder='Знайти...']")));
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = 'iPad'; arguments[0].dispatchEvent(new Event('input'));", searchField);
        Thread.sleep(1000);
//        searchField.sendKeys(Keys.ENTER);
//        WebElement searchButton = driver.findElement(By.cssSelector("[class='search-button-first-form']"));
//        searchButton.click();

        WebElement form = driver.findElement(By.cssSelector("div.search-form.header-search-form form"));
        form.submit();

        fluentWaiter.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[class*='product-wrapper']"), 30));

        List<WebElement> iPadProducts = driver.findElements(By.cssSelector("[class*='product-wrapper']"));
        WebElement secondIPad = iPadProducts.get(secondProduct);
        String secondIPadName = secondIPad.findElement(By.cssSelector("[class='description-wrapper']")).getText();

        System.out.println("secondIPad:" + secondIPadName);

        WebElement secondIPadBuyButton = secondIPad.findElement(By.cssSelector("[class='br-bb-buy'"));
        secondIPadBuyButton.click();

        WebElement cart = fluentWaiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='modal-content']")));

        WebElement productNameFromCartElement = cart.findElement(By.cssSelector("[class='br-ci-name']"));
        String productNameFromCart = productNameFromCartElement.getText();

        System.out.println("productNameFromCart:"+productNameFromCart);

        Assert.assertEquals(secondIPadName, productNameFromCart);

        driver.quit();
    }

}