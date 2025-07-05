package hw_6.practice;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Test2 extends BaseTest {

    @Test
    public void verifyTitleWithWait() {
        WebElement searchField = getDriver().findElement(By.name("search"));
        searchField.sendKeys("iPhone");

        WebElement searchButton = getDriver().findElement(By.cssSelector("[class*='search-form__submit']"));
        searchButton.click();

        WebElement titleElement = getDriver().findElement(By.cssSelector(".catalog-heading"));

        Assert.assertTrue(titleElement.isDisplayed());
    }

    @Test
    public void verifyProductCanBeAddedToBasket() {
        int secondProduct = 1;

        FluentWait<WebDriver> fluentWaiter = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchFieldError.class);

        WebElement searchField = getDriver().findElement(By.name("search"));
        searchField.sendKeys("iPad");

        WebElement searchButton = getDriver().findElement(By.cssSelector("[class*='search-form__submit']"));
        searchButton.click();

        fluentWaiter.until(ExpectedConditions.numberOfElementsToBe(By.tagName("rz-product-tile"), 60));

        List<WebElement> iPadProducts = getDriver().findElements(By.tagName("rz-product-tile"));
        WebElement secondIPad = iPadProducts.get(secondProduct);
        String secondIPadName = secondIPad.findElement(By.cssSelector(".text-base")).getText();

        System.out.println(secondIPadName);

        WebElement secondIPadBuyButton = secondIPad.findElement(By.cssSelector(".buy-button"));
        secondIPadBuyButton.click();

        fluentWaiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='header-cart__button'] .badge")));

        WebElement cartButton = getDriver().findElement(By.cssSelector("[class='header-cart__button']"));
        cartButton.click();

        fluentWaiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='cart-product__title']")));

        WebElement productNameFromCartElement = getDriver().findElement(By.cssSelector("[class='cart-product__title']"));
        String productNameFromCart = productNameFromCartElement.getText();

        System.out.println(productNameFromCart);

        Assert.assertEquals(secondIPadName, productNameFromCart);
    }

}
