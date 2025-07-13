package hw_8_pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {

    //private final ElementsCollection goodsInCart = $$("div.br-ci-name a").shouldHave(CollectionCondition.sizeGreaterThan(0));
    public String getPageTitle() {
        return $("div.title h1").getText();
    }

    public void addToCard(){
        SelenideElement buyButton = $("a.add").shouldBe(clickable);
        buyButton.click();
    }

    public List<String> getCardGoods() {
        ElementsCollection goodsInCart = $$("div.br-ci-name a").shouldHave(CollectionCondition.sizeGreaterThan(0));
        return goodsInCart.stream().map(SelenideElement::getText).collect(Collectors.toList());
    }
}
