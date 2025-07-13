package hw_6.practice;


//import hw_6.practice.listeners.TstListener;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners(hw_6.practice.listeners.TstListener.class)
public class NegativeTests extends BaseTest {

    @Test
    public void searchInvalidTerm() {
        String invalidSearch = "!@#$%^&*";

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement searchField = getDriver().findElement(By.cssSelector("div[class*='search__autosuggest'] input[type='text']"));
        searchField.sendKeys(invalidSearch, Keys.ENTER);

        WebElement notFoundMsg = getDriver().findElement(By.cssSelector("[class*='search__no-items-title']"));
        Assert.assertTrue(notFoundMsg.getText().contains("нічого не знайдено"));
    }
}
