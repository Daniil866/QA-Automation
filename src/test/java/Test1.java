package hw_4;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");

        sleep(3000);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        sleep(3000);

        WebElement googlePlayElement = driver.findElement(By.cssSelector(".footer-top__app-images [alt='Google Play']"));
        googlePlayElement.click();

        sleep(3000);

        String secondTab = new ArrayList<>(driver.getWindowHandles()).get(1);
        driver.switchTo().window(secondTab);

        WebElement installButton = driver.findElement(By.xpath("//span[contains(text(), 'Install')]"));

        Assert.assertTrue(installButton.isDisplayed());

        sleep(3000);
        driver.quit();
    }

}
