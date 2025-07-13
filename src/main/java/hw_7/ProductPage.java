package hw_7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait waiter;

    private static final By TITLE_ELEMENT = By.cssSelector("h1.title__main");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public String getTitleText() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ELEMENT));
        return driver.findElement(TITLE_ELEMENT).getText();
    }

}
