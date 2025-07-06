package hw_8.selenide_first_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FirstExampleTest extends BaseTest {

    @Test
    public void verifyTitleIsCorrect() {
        String wordToFind = "iPhone";

        $("input[type='text']").setValue(wordToFind);
        sleep(3000);
        $("button.search__btn").click();

        String searchedPageTitle = $("h1.catalog-title__main").shouldBe(visible).getText();

        Assert.assertTrue(searchedPageTitle.contains(wordToFind));
    }

    @Test
    public void verifyTitleIsCorrect1() {
        String wordToFind = "iPhone";

        $("input[type='text']").setValue(wordToFind);
        sleep(3000);
        $("button.search__btn").click();

        String searchedPageTitle = $("h1.catalog-title__main").shouldBe(visible).getText();

        Assert.assertTrue(searchedPageTitle.contains(wordToFind));
    }

}
