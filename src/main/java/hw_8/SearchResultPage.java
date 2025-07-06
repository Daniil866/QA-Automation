package hw_8;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static java.time.Duration.ofSeconds;

public class SearchResultPage {

    private static final ElementsCollection PRODUCT_ELEMENTS = $$("div.list-item");

    public String getProductName(int productIndex) {
        SelenideElement product = PRODUCT_ELEMENTS.shouldHave(sizeGreaterThan(productIndex), ofSeconds(5)).get(productIndex - 1);
        return product.$("[class='list-item__title-container m_b-5']").getText();
    }

    public void navigateToProduct(int productIndex) {
        SelenideElement product = PRODUCT_ELEMENTS.get(productIndex - 1);
        product.shouldBe(visible).$(".list-item__photo").click();
    }

}
