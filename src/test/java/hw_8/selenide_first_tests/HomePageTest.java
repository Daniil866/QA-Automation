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
    public void verifyTitleIsCorrect1() {
        String wordToFind = "iPhone";

        $("input[type='text']").setValue(wordToFind);
        sleep(3000);
        $("button.search__btn").click();

        String searchedPageTitle = $("h1.catalog-title__main").shouldBe(visible).getText();

        Assert.assertTrue(searchedPageTitle.contains(wordToFind));
    }

}
