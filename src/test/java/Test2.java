package hw_2;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test2 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");

        WebElement hamburgerButton = driver.findElement(By.cssSelector("[data-testid='menu_button']"));
        hamburgerButton.click();

        WebElement sideMenu = driver.findElement(By.cssSelector("[class='burger']"));

        Assert.assertTrue(sideMenu.isDisplayed());

        driver.quit();
    }

}