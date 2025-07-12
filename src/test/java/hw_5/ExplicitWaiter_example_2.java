package hw_5;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("https://hotline.ua/");

        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement katalogbutton = driver.findElement(By.cssSelector("[class=\"button-menu-main \"]"));
        katalogbutton.click();

        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='menu-main__list']")));

        List<WebElement> sideMenu = driver.findElements(By.cssSelector("[class='menu-main__list']"));

        WebElement menuItem = driver.findElement(By.cssSelector("[class=\"menu-main__item\"]"));
        Assert.assertTrue(menuItem.isDisplayed());

        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"menu-main__close btn-close\"]")));

        WebElement closeSideMenuButton = driver.findElement(By.cssSelector("[class=\"menu-main__close btn-close\"]"));
        closeSideMenuButton.click();

        waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class=\"menu-main active\"]")));

        List<WebElement> sideMenuElementsAfterClose = driver.findElements(By.cssSelector("[class=\"menu-main active\"]"));
        Assert.assertEquals(sideMenuElementsAfterClose.size(), 0);

        driver.quit();
    }

}