package hw_8;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class HomePage {

    public void enterTextIntoSearchField(String textToEnter) {
        $("[type='Text']").setValue(textToEnter);
        sleep(3000);
    }

    public void clickOnSearchButton() {
        $("button.search__btn").click();
    }

}
