package hw_7;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageTests extends BaseTest {

    @Test
    public void verifyPopUpIsDisplayedAfterClickingOnGoodsCatalogButton() {
        HomePage homePage = new HomePage(getDriver());

        WebDriverWait loadPage = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        loadPage.until(ExpectedConditions.elementToBeClickable(homePage.getGoodsCatalogButton()));
        homePage.clickOnGoodsCatalogButton();

        Assert.assertTrue(homePage.isGoodsCatalogMenuDisplayed());
    }

    @Test
    public void verifyTitleOfSrpIsCorrectAccordingToEnteredSearchKeyWord() {
        String wordToSearch = "iPhone";

        HomePage homePage = new HomePage(getDriver());
        homePage.enterTextIntoSearchField(wordToSearch);

        WebDriverWait loadPage = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        loadPage.until(ExpectedConditions.elementToBeClickable(homePage.getGoodsCatalogButton()));
        homePage.clickOnSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        String titleText = searchResultPage.getTitleText();

        Assert.assertTrue(titleText.contains(wordToSearch));
    }

}