package hw_10.step_definitions;

import hw_10.SearchResultPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SearchResultPageStepDefinitions extends BaseStepDefinition {

    private SearchResultPage searchResultPage = new SearchResultPage();

    @When("User clicks on {int} product")
    public void clickOnProduct(int productIndex) {
        String productNameToSave = searchResultPage.getProductName(productIndex);
        dataContext.put("productNameToNavigated", productNameToSave);
        searchResultPage.clickOnProduct(productIndex);
    }

    @Then("verify Search Result Page title contains {string} word")
    public void verifyPageTitle(String wordToVerify) {
        String actualPageTitle = searchResultPage.getPageTitle();
        Assert.assertTrue(actualPageTitle.contains(wordToVerify));
    }

}
