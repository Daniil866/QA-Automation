package hw_8.selenide_page_object;

import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    private static final String BASE_URL = "https://hotline.ua/";

    @BeforeMethod
    public void openSite() {
        open(BASE_URL);
    }

}
