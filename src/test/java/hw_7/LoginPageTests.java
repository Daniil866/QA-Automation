package hw_7;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Timer;
import static java.lang.Thread.sleep;

public class LoginPageTests extends BaseTest{
    @Test
    public void tryLoginToSite() throws InterruptedException {
        String LoginText = "user@gmail.com";
        String PassText = "qwerty";

        HomePage homePage = new HomePage(getDriver());
        WebDriverWait loadPage = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        loadPage.until(ExpectedConditions.elementToBeClickable(homePage.getLoginButton()));
        homePage.clickOnLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        WebDriverWait loadLoginPage = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        //loginPage.inputCapcha();

        //loginPage.enterLogin(LoginText);
        //loginPage.enterPass(PassText);
    }
}
