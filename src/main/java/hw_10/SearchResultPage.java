package hw_10;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage {

    private static final ElementsCollection PRODUCTS = $$("div.list-item");

    public String getPageTitle() {
        return $("h1.catalog-title__main").shouldBe(visible).getText();
    }

    public void clickOnProduct(int productIndex) {
        SelenideElement product = PRODUCTS.get(productIndex - 1);
        product.$(".list-item__photo").click();
    }

    public String getProductName(int productIndex) {
        SelenideElement product = PRODUCTS.get(productIndex - 1);
        return product.$(".list-item__info div.list-item__title-container").getText();
    }

}
