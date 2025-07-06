package hw_10.step_definitions;

import aqa_lecture_12.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.sleep;

public class HomePageStepDefinitions {

    private HomePage homePage = new HomePage();

    @Given("User opens Home Page")
    public void openHomePage() {
        homePage.openHomePage();
    }

    @When("User enters {string} word into search filed on Home Page")
    public void enterWordIntoSearchField(String wordToEnter) {
        homePage.enterWordToSearchField(wordToEnter);
    }

    @When("User clicks on search button on Home Page")
    public void clickOnSearchButton() {
        homePage.clickOnSearchButton();
        sleep(3000);
    }

    @When("User clicks on login button on Home Page")
    public void clickOnLoginButton() {
        homePage.clickOnLoginButton();
    }

}
