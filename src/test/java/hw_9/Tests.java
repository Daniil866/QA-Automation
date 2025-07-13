package hw_9;

import org.junit.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Tests extends BaseTest {

    @Test
    public void verifyContactInfoPage() {
        testCaseId = 1;
        System.out.println("First test execution");

        HomePage homePage = new HomePage();

        homePage.goToContactPage();
        String pageTitle = $("div.br-body h2").shouldBe(visible).getText();

        System.out.println("current page title :" + pageTitle);

        Assert.assertTrue(pageTitle.toLowerCase().contains("контакти"));
}

    @Test
    public void verifyUserCanBeNavigatedToTheProductDetailsPage() {
        testCaseId = 2;
        System.out.println("Second test execution");
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
    public void verifyUserCanBeNavigatedToTheReminderPage() {
        testCaseId = 3;
        System.out.println("Third test execution");
        String loginText = "+38(000)0000000";
        String passText = "qwerty";
        String ErrText = "Некоректний";
        HomePage homePage = new HomePage();

        homePage.goToLoginPage();

        homePage.inputLoginText(loginText);
        homePage.inputPasswordText(passText);

        $("button[class='br-login-submit']").shouldBe(visible).click();

        String LoginTextError = $("div.login-error").shouldBe(visible).getText();

        homePage.clickOnRecoverPass();

        String errTextNumber = $("label.error.error_msg.empty_field").shouldBe(visible).getText();

        Assert.assertTrue(errTextNumber.contains(ErrText));
    }

}
