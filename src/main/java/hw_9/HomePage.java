package hw_9;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class HomePage {

    public void enterTextIntoSearchField(String textToEnter) {
        $("div[class='header-bottom-in'] div[class='search-form header-search-form'] input[class='quick-search-input']").shouldBe(visible).setValue(textToEnter);
    }

    public void clickOnSearchButton() {
        //$("div[class='qsr-block'] div[class='qsr-form'] input.qsr-submit, "+
        //            "div[class='header-bottom-in'] div[class='search-form header-search-form'] input[class='search-button-first-form']"
        //        ).click();
        $("div[class='header-bottom-in'] div[class='search-form header-search-form'] input[class='quick-search-input']").sendKeys(Keys.ENTER);
    }

    public void goToContactPage() {
        $("div.br-f-bgr div.br-f-menus a[href*='contacts']").shouldBe(clickable).click();
    }
    public void goToLoginPage() {
        $("button.auth-popup-button").shouldBe(clickable).click();
    }

    public void inputLoginText(String LoginText) {
        $("form[class*='br-login-form modal-br-login-form'] input[class='input-field phone_mask']").shouldBe(visible).setValue(LoginText);
    }
    public void inputPasswordText(String PasswordText) {
        $("form[class*='br-login-form modal-br-login-form'] input[class='input-field br-login-pass-field']").shouldBe(visible).setValue(PasswordText);
    }

    public void clickOnRecoverPass() {
        $("button.br-login-button-toggle").shouldBe(clickable).click();
    }

}