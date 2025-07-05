package hw_6.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    private WebDriver driver;

    private static final String BASE_URL = "https://rozetka.com.ua/";

    @BeforeMethod
    public void webDriverInit() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void driverClose() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
