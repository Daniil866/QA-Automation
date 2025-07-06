package hw_10.step_definitions;

import aqa_lecture_12.ProductPage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ProductPageStepDefinitions extends BaseStepDefinition {

    private ProductPage productPage = new ProductPage();

    @Then("verify user is navigated to the correct Product Page")
    public void verifyProductName() {
        String expectedProductName = dataContext.get("productNameToNavigated");
        String actualProductName = productPage.getProductName();
        Assert.assertTrue(actualProductName.contains(expectedProductName));
    }

}
