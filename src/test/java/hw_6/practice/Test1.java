package hw_6.practice;

import data_provider.SearchTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends BaseTest {

    @Test(dataProvider = "getSearchData", dataProviderClass = SearchTestData.class)
    public void searchTermsVerification(String wordToSearch, String expectedTitle) {
        WebElement searchField = getDriver().findElement(By.name("search"));
        searchField.sendKeys(wordToSearch, Keys.ENTER);

        WebElement pageTitleElement = getDriver().findElement(By.cssSelector(".catalog-heading"));
        String actualTitle = pageTitleElement.getText();

        Assert.assertEquals(actualTitle, expectedTitle);
    }

}