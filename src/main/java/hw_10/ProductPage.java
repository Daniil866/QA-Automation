package hw_10;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    public String getProductName() {
        return $("[class='title']").getText();
    }

}
