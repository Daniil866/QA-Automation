import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class HotlineBaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Selenium Manager автоматически загрузит ChromeDriver.
        // Не нужно вручную указывать System.setProperty() или использовать WebDriverManager.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        // options.addArguments("--headless"); // Раскомментируйте для запуска тестов без открытия браузера

        driver = new ChromeDriver(options); // Selenium Manager найдет драйвер автоматически

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Явное ожидание

        driver.get("https://hotline.ua/");
        // Для базового теста, мы просто убедимся, что страница загрузилась.
        // Проверка URL входа в конкретных тестах.
        System.out.println("Navigated to Hotline.ua main page.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрываем браузер
            System.out.println("Browser closed.");
        }
    }
}