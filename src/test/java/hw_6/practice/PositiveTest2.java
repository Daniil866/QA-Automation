package hw_6.practice;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class PositiveTest2 extends BaseTest {

    @Test
    public void verifyTitleWithWait() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement searchField = getDriver().findElement(By.cssSelector("div[class*='search__autosuggest'] input[type='text']"));
        searchField.sendKeys("iPhone");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[role='listbox'] li[data-suggestion-index='0']")));

        WebElement searchElement = getDriver().findElement(By.cssSelector("li[data-suggestion-index='0']"));
        searchElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".catalog-title")));
        WebElement titleElement = getDriver().findElement(By.cssSelector(".catalog-title"));

        Assert.assertTrue(titleElement.isDisplayed());
    }

    @Test
    public void verifyProductCanBeSelected() {

        FluentWait<WebDriver> fluentWaiter = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchFieldError.class);

        WebElement searchField = getDriver().findElement(By.cssSelector("div[class*='search__autosuggest'] input[type='text']"));
        searchField.sendKeys("iPad");

        fluentWaiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[role='listbox'] li[data-suggestion-index='0']")));
        WebElement searchButton = getDriver().findElement(By.cssSelector("ul[role='listbox'] li[data-suggestion-index='0']"));
        searchButton.click();

        fluentWaiter.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.list-item"), 4));

        List<WebElement> iPadProducts = getDriver().findElements(By.cssSelector("div.list-item"));
        WebElement secondProduct = iPadProducts.get(iPadProducts.size()-1);
        String secondProductName = secondProduct.findElement( By.cssSelector("[class='list-item__title-container m_b-5']")).getText();

        System.out.println("secondProductName: " + secondProductName);

        WebElement secondIPadBuyButton = secondProduct.findElement(By.cssSelector(".list-item__photo-buttons-container"));
        secondIPadBuyButton.click();

        fluentWaiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.title__main")));

        WebElement productNameProductPage = getDriver().findElement(By.cssSelector("h1.title__main"));
        String productNameFromCart = productNameProductPage.getText();

        System.out.println("productNameFromCart: " + productNameFromCart);

        Assert.assertTrue(productNameFromCart.toLowerCase().contains(secondProductName.toLowerCase()));
    }

}
