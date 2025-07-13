package hw_10.step_definitions;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.codeborne.selenide.WebDriverRunner.url;

public class BrowserValidationStepDefinitions {

    @When("waits for {int} seconds")
    public void waitForSeconds(int secondsToWait) {
        Selenide.sleep(secondsToWait * 1000L);
    }

    @Then("verify url ends with {string}")
    public void verifyBrowserUrl(String urlEnd) {
        String actualUrl = url();
        Assert.assertTrue(actualUrl.endsWith(urlEnd));
    }

}
