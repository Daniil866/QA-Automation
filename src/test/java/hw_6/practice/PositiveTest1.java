package hw_6.practice;

import hw_6.data_provider.SearchTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners(hw_6.practice.listeners.TstListener.class)
public class PositiveTest1 extends BaseTest {


    @Test(dataProvider = "getSearchData", dataProviderClass = SearchTestData.class)
    public void searchTermsVerification(String wordToSearch, String expectedTitle) {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

        WebElement searchField = getDriver().findElement(By.cssSelector("[type='text']"));
        searchField.sendKeys(wordToSearch);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[role='listbox'] li[data-suggestion-index='0']")));

        WebElement searchElement = getDriver().findElement(By.cssSelector("li[data-suggestion-index='0']"));
        searchElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".catalog-title, .catalog-title__main")));
        WebElement pageTitleElement = getDriver().findElement(By.cssSelector(".catalog-title, .catalog-title__main"));
        String actualTitle = pageTitleElement.getText();

        Assert.assertEquals(actualTitle, expectedTitle);
    }

}