package hw_10.step_definitions;

import hw_10.LoginPage;
import io.cucumber.java.en.When;

public class LoginPageStepDefinition {

    private LoginPage loginPage = new LoginPage();

    @When("User clicks on forgot password link on Login Page")
    public void clickOnForgotPasswordLink() {
        loginPage.clickOnForgotPasswordLink();
    }

}
