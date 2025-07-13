package hw_8_pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage {

    private static final ElementsCollection PRODUCT_ELEMENTS = $$("div.col-lg-3.product-wrapper")
            .shouldHave(CollectionCondition.sizeGreaterThan(20));

    public String getProductName(int productIndex) {
        SelenideElement product = PRODUCT_ELEMENTS.get(PRODUCT_ELEMENTS.size() - productIndex);
        return product.$("div.br-pp-desc").getText();
    }

    public void navigateToProduct(int productIndex) {
        SelenideElement product = PRODUCT_ELEMENTS.get(PRODUCT_ELEMENTS.size() - productIndex);
        String slug = product.getAttribute("data-slug");
        product.shouldBe(clickable).$("a[href*='" + slug + "']").click();
    }

}
