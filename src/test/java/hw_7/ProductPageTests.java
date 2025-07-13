package hw_7;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class ProductPageTests extends BaseTest {

    @Test
    public void verifyUserIsNavigatedToTheCorrectPage() {
        String wordToSearch = "Samsung";
        int thirdProduct;

        HomePage homePage = new HomePage(getDriver());
        homePage.enterTextIntoSearchField(wordToSearch);

        WebDriverWait loadPage = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        loadPage.until(ExpectedConditions.elementToBeClickable(homePage.getSearchButton()));
        homePage.clickOnSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        thirdProduct = searchResultPage.getProductCount() - 1;
        String expectedProductName = searchResultPage.getProductName(thirdProduct);
        searchResultPage.navigateToTheProduct(thirdProduct);

        searchResultPage.WaitProductSpecification(30);

        ProductPage productPage = new ProductPage(getDriver());
        String actualProductName = productPage.getTitleText();

        Assert.assertTrue(actualProductName.toLowerCase().contains(expectedProductName.toLowerCase()),
                "Actual product title does not match expected: " + expectedProductName);

        String normalizedExpectedName = expectedProductName
                .toLowerCase()
                .replaceAll("[^a-z0-9а-яіїєґ]+", "-")
                .replaceAll("^-+|-+$", "");
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertNotNull(currentUrl);
        Assert.assertTrue(currentUrl.contains("product") || currentUrl.contains("goods") || currentUrl.toLowerCase().contains(normalizedExpectedName),
                "URL does not look like a product page: " + currentUrl);

        searchResultPage.waitForSeconds(5);
    }

}