package hw_10;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class HomePage {

    public void openHomePage() {
        Selenide.open("https://hotline.ua/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    public void enterWordToSearchField(String wordToSearch) {
        $("input[type='text']").setValue(wordToSearch);
        sleep(3000);
    }

    public void clickOnSearchButton() {
        $("button.search__btn").click();
    }

    public void clickOnLoginButton() {
        $("a.header__user-icon").click();
    }

}
