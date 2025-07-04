package hw_5;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ExplicitWaiter_example_2 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.olx.ua/uk/");

        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement hamburgerButton = driver.findElement(By.cssSelector("[data-testid='menu_button']"));
        hamburgerButton.click();

        List<WebElement> sideMenu = driver.findElements(By.cssSelector("[class='burger']"));

        Assert.assertTrue(sideMenu.get(0).isDisplayed());

        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testId='side_menu_close']")));

        WebElement closeSideMenuButton = driver.findElement(By.cssSelector("[data-testId='side_menu_close']"));
        closeSideMenuButton.click();

        waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class='burger']")));

        List<WebElement> sideMenuElementsAfterClose = driver.findElements(By.cssSelector("[class='burger']"));
        Assert.assertEquals(sideMenuElementsAfterClose.size(), 0);

        driver.quit();
    }

}