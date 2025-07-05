package hw_7;

import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

import hw_7.HomePage;
import hw_7.SearchResultPage;
import hw_7.ProductPage;


public class HomePageTests extends BaseTest {

    @Test
    public void verifyPopUpIsDisplayedAfterClickingOnGoodsCatalogButton() throws InterruptedException {
        HomePage homePage = new HomePage(getDriver());

        sleep(5000);

        homePage.clickOnGoodsCatalogButton();

        Assert.assertTrue(homePage.isGoodsCatalogMenuDisplayed());
    }

    @Test
    public void verifyTitleOfSrpIsCorrectAccordingToEnteredSearchKeyWord() throws InterruptedException {
        String wordToSearch = "iPhone";

        HomePage homePage = new HomePage(getDriver());
        homePage.enterTextIntoSearchField(wordToSearch);
        sleep(3000);
        homePage.clickOnSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        String titleText = searchResultPage.getTitleText();

        Assert.assertTrue(titleText.contains(wordToSearch));
    }

}