package hw_7;

import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

import hw_7.HomePage;
import hw_7.SearchResultPage;
import hw_7.ProductPage;


public class ProductPageTests extends BaseTest {

    @Test
    public void verifyUserIsNavigatedToTheCorrectPage() throws InterruptedException {
        String wordToSearch = "Apple";
        int thirdProduct = 3;

        HomePage homePage = new HomePage(getDriver());
        homePage.enterTextIntoSearchField(wordToSearch);
        sleep(3000);
        homePage.clickOnSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        String expectedProductName = searchResultPage.getProductName(thirdProduct);
        searchResultPage.navigateToTheProduct(thirdProduct);

        ProductPage productPage = new ProductPage(getDriver());
        String actualProductName = productPage.getTitleText();

        Assert.assertTrue(actualProductName.contains(expectedProductName));

        sleep(6000);
    }

}