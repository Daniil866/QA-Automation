package hw_8.selenide_page_object;

import hw_8_pages.HomePage;
import hw_8_pages.ProductPage;
import hw_8_pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {

    @Test
    public void verifyUserIsNavigatedToTheCorrectPage() {
        String wordToFind = "Apple MacBook";
        int productToNavigate = 3;

        HomePage homePage = new HomePage();
        homePage.enterTextIntoSearchField(wordToFind);
        homePage.clickOnSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage();
        String expectedProductName = searchResultPage.getProductName(productToNavigate);
        searchResultPage.navigateToProduct(productToNavigate);

        ProductPage productPage = new ProductPage();
        String actualProductName = productPage.getPageTitle();

        Assert.assertTrue(actualProductName.contains(expectedProductName));
    }

}
