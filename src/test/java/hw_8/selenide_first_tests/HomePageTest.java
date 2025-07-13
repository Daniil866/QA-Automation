package hw_8.selenide_first_tests;

import hw_8_pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyProductSearchCorrect() {
        String wordToFind = "iPhone";
        HomePage homePage = new HomePage();

        homePage.enterTextIntoSearchField(wordToFind);

        homePage.clickOnSearchButton();

        String searchedPageTitle = $("div[class*='search-result']").shouldBe(visible).getText();
        System.out.println("searchedPageTitle:" + searchedPageTitle);
        Assert.assertTrue(searchedPageTitle.toLowerCase().contains(wordToFind.toLowerCase()));
    }

    @Test
    public void verifyTryLogin() {
        String loginText = "+38(000)0000000";
        String passText = "qwerty";
        String ErrText = "Некоректний";
        HomePage homePage = new HomePage();

        homePage.goToLoginPage();

        homePage.inputLoginText(loginText);
        homePage.inputPasswordText(passText);

        $("button[class='br-login-submit']").shouldBe(visible).click();

        String LoginTextError = $("div.login-error").shouldBe(visible).getText();

        Assert.assertTrue(LoginTextError.contains(ErrText));
    }

}
