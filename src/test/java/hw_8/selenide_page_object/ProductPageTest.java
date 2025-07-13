package hw_8.selenide_page_object;

import hw_8_pages.HomePage;
import hw_8_pages.ProductPage;
import hw_8_pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static java.lang.Thread.sleep;

public class ProductPageTest extends BaseTest {

    @Test
    public void verifyIsCorrectPage() {
        String wordToFind = "Samsung Galaxy S24";
        int productToNavigate = 5;

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

    @Test
    public void verifyAddToCard() throws InterruptedException {
        String wordToFind = "Samsung Galaxy S24";
        int productToNavigate = 5;

        HomePage homePage = new HomePage();

        homePage.enterTextIntoSearchField(wordToFind);
        homePage.clickOnSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage();
        String expectedProductName = searchResultPage.getProductName(productToNavigate);
        searchResultPage.navigateToProduct(productToNavigate);

        System.out.println("expectedProductName :" + expectedProductName);

        ProductPage productPage = new ProductPage();
        String actualProductName = productPage.getPageTitle();

        productPage.addToCard();

        List<String> productsInCart = productPage.getCardGoods();

        System.out.println("productsInCart :" + productsInCart);

        productsInCart.stream().anyMatch(name -> name.contains(expectedProductName));
        //Assert.assertTrue(productsInCart.contains(expectedProductName), "Goods in cart!");
    }

}
