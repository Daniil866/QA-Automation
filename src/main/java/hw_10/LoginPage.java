package hw_10;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public void clickOnForgotPasswordLink() {
        $x("//a[contains(text(), 'Забули пароль?')]").shouldBe(visible).click();
    }

}
