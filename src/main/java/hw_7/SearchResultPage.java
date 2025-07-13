package hw_7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultPage {

    private WebDriver driver;
    private WebDriverWait waiter;

    private static final By PAGE_TITLE = By.cssSelector("h1.catalog-title__main, div.search__title");
    private static final By PRODUCT_ELEMENTS = By.cssSelector("div.list-item");
    private static final By PRODUCT_ELEMENT_NAME = By.cssSelector("[class='list-item__title-container m_b-5']");
    private static final By PRODUCT_ELEMENT_IMAGE = By.cssSelector(".list-item__photo-buttons-container");
    private static final By PRODUCT_SPECIFICATION = By.cssSelector("div.specifications");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String getTitleText() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
        WebElement titleElement = driver.findElement(PAGE_TITLE);
        return titleElement.getText();
    }

    public String getProductName(int indexProduct) {
        waiter.until(ExpectedConditions.numberOfElementsToBeMoreThan(PRODUCT_ELEMENTS, indexProduct - 1));
        List<WebElement> productsList = driver.findElements(PRODUCT_ELEMENTS);
        WebElement productElement = productsList.get(indexProduct - 1);
        WebElement productName = productElement.findElement(PRODUCT_ELEMENT_NAME);
        return productName.getText();
    }

    public void navigateToTheProduct(int indexProduct) {
        List<WebElement> productsList = driver.findElements(PRODUCT_ELEMENTS);
        WebElement productElement = productsList.get(indexProduct - 1);
        productElement.findElement(PRODUCT_ELEMENT_IMAGE).click();
    }

    public int getProductCount() {
        List<WebElement> productsList = driver.findElements(PRODUCT_ELEMENTS);
        return productsList.size();
    }

    public void WaitProductSpecification(int waitTime) {
        waiter = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        waiter.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_SPECIFICATION));
    }

    public void waitForSeconds(int seconds) {
        long end = System.currentTimeMillis() + (seconds * 1000L);

        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(driver -> System.currentTimeMillis() >= end);
    }

}
