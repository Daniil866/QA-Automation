package hw_7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    public void driverInit() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://hotline.ua/");
    }

    @AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

}