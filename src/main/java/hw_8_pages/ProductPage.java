package hw_8_pages;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    public String getPageTitle() {
        return $("h1.title__main").getText();
    }

}
