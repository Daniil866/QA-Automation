package hw_8.selenide_page_object;

import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Configuration;

public class BaseTest {

    private static final String BASE_URL = "https://brain.com.ua/";

    @BeforeMethod
    public void openSite() {
        open(BASE_URL);
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
    }

}
