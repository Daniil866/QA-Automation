package hw_7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait waiter;

    private static final By GOODS_CATALOG_BUTTON = By.cssSelector("div.button-menu-main");
    private static final By GOODS_CATALOG_MENU = By.cssSelector("ul.menu-main__list");
    private static final By SEARCH_FIELD = By.cssSelector("[type='text']");
    private static final By SEARCH_BUTTON = By.cssSelector("button.search__btn");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickOnGoodsCatalogButton() {
        driver.findElement(GOODS_CATALOG_BUTTON).click();
    }

    public boolean isGoodsCatalogMenuDisplayed() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(GOODS_CATALOG_MENU));
        return driver.findElement(GOODS_CATALOG_MENU).isDisplayed();
    }

    public void enterTextIntoSearchField(String textToEnter) {
        driver.findElement(SEARCH_FIELD).sendKeys(textToEnter);
    }

    public void clickOnSearchButton() {
        driver.findElement(SEARCH_BUTTON).click();
    }

}
