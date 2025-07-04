package hw_2;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static java.lang.Thread.sleep;

public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");

        WebElement hamburgerButton = driver.findElement(By.cssSelector("[data-testid='menu_button']"));
        hamburgerButton.click();

        List<WebElement> sideMenu = driver.findElements(By.cssSelector("[class='burger']"));

        Assert.assertTrue(sideMenu.get(0).isDisplayed());

        sleep(3000);

        WebElement closeSideMenuButton = driver.findElement(By.cssSelector("[data-testId='side_menu_close']"));
        closeSideMenuButton.click();

        sleep(3000);

        List<WebElement> sideMenuElementsAfterClose = driver.findElements(By.cssSelector("[class='burger']"));
        Assert.assertEquals(sideMenuElementsAfterClose.size(), 0);

        sleep(3000);

        driver.quit();
    }

}