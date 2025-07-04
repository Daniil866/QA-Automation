package hw_4;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static java.lang.Thread.sleep;

public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        int secondProduct = 1;

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");

        WebElement searchField = driver.findElement(By.name("search"));
        searchField.sendKeys("iPad");
        sleep(3000);
        WebElement searchButton = driver.findElement(By.cssSelector("[class*='search-form__submit']"));
        searchButton.click();

        sleep(3000);

        List<WebElement> iPadProducts = driver.findElements(By.tagName("rz-product-tile"));
        WebElement secondIPad = iPadProducts.get(secondProduct);
        String secondIPadName = secondIPad.findElement(By.cssSelector(".text-base")).getText();

        System.out.println(secondIPadName);

        WebElement secondIPadBuyButton = secondIPad.findElement(By.cssSelector(".buy-button"));
        secondIPadBuyButton.click();
        sleep(3000);

        WebElement cartButton = driver.findElement(By.cssSelector("[class='header-cart__button']"));
        cartButton.click();

        sleep(3000);

        WebElement productNameFromCartElement = driver.findElement(By.cssSelector("[class='cart-product__title']"));
        String productNameFromCart = productNameFromCartElement.getText();

        System.out.println(productNameFromCart);

        Assert.assertEquals(secondIPadName, productNameFromCart);

        driver.quit();
    }

}
