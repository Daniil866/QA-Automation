package hw_7;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait waiter;

    private static final By PAGE_TITLE = By.cssSelector("div.template-page");
    private static final By USERNAME_FORM = By.cssSelector("[class*='login-form__field-login']");
    private static final By PASSWORD_FORM = By.cssSelector("[class*='login-form__field-password']");
    private static final By CAPCHA_FORM = By.cssSelector("iframe[src*='cloudflare.com'],iframe[title*='Cloudflare'], iframe[title='Віджет, що містить завдання безпеки Cloudflare'], [class*='login-form__captcha']");
    private static final By CAPCHA_CHECKBOX = By.cssSelector("input[type='checkbox'], div.mark, label.cb-lb");

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public String getTitleText() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
        return driver.findElement(PAGE_TITLE).getText();
    }

    public void inputCapcha(){
        try {
            this.waiter = new WebDriverWait(driver, Duration.ofSeconds(60));
            waiter.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(CAPCHA_FORM));

            List<WebElement> captchaFrames = driver.findElements(CAPCHA_FORM);

            if (captchaFrames.isEmpty()) {
                return;
            }

            WebElement checkbox = waiter.until(ExpectedConditions.elementToBeClickable(CAPCHA_CHECKBOX));
            checkbox.click();
            System.out.println("CAPTCHA пройдена.");
            driver.switchTo().defaultContent();

        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("CAPTCHA не була пройдена: " + e.getMessage());
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Сталася помилка при обробці CAPTCHA: " + e.getMessage());
            driver.switchTo().defaultContent();
        }
    }

    public void enterLogin(String LoginText) {
        this.waiter = new WebDriverWait(driver, Duration.ofSeconds(20));
        waiter.until(ExpectedConditions.elementToBeClickable(USERNAME_FORM));
        driver.findElement(USERNAME_FORM).sendKeys(LoginText);
    }
    public void enterPass(String PassText) {
        this.waiter = new WebDriverWait(driver, Duration.ofSeconds(20));
        waiter.until(ExpectedConditions.elementToBeClickable(PASSWORD_FORM));
        driver.findElement(PASSWORD_FORM).sendKeys(PassText);
    }
}
