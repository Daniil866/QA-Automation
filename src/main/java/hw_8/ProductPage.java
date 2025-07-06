package hw_8;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    public String getPageTitle() {
        return $("h1.title__main").getText();
    }

}
