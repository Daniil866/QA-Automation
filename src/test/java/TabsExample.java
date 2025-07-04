package hw_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class TabsExample {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://rozetka.com.ua/");

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        sleep(3000);

        String firstTabId = tabs.get(0);
        String secondTabId = tabs.get(1);
        String thirdTabId = tabs.get(2);
        String forceTabId = tabs.get(3);
        String fifthTabId = tabs.get(4);

        System.out.println("-----SWITCH TO SECOND TAB---");

        driver.switchTo().window(secondTabId);

        sleep(3000);

        WebElement secondTab = driver.findElement(By.cssSelector(".catalog-heading"));
        String titleOgSecondTab = secondTab.getText();

        System.out.println(titleOgSecondTab);

        System.out.println("-----SWITCH TO FIRST TAB---");

        sleep(3000);

        driver.switchTo().window(firstTabId);

        WebElement firstTab = driver.findElement(By.cssSelector(".catalog-heading"));
        String titleOfFirstTab = firstTab.getText();

        System.out.println(titleOfFirstTab);

        System.out.println("-----SWITCH TO FORCE TAB---");

        sleep(3000);

        driver.switchTo().window(forceTabId);

        WebElement forceTab = driver.findElement(By.cssSelector(".catalog-heading"));
        String titleOfForceTab = forceTab.getText();

        System.out.println(titleOfForceTab);

        System.out.println("-----SWITCH TO THIRD TAB---");

        sleep(3000);

        driver.switchTo().window(thirdTabId);

        WebElement thirdTab = driver.findElement(By.cssSelector(".catalog-heading"));
        String titleOfThirdTab = thirdTab.getText();

        System.out.println(titleOfThirdTab);

        System.out.println("-----SWITCH TO FIFTH TAB---");

        sleep(3000);

        driver.switchTo().window(fifthTabId);

        WebElement fifthTab = driver.findElement(By.cssSelector(".catalog-heading"));
        String titleOfFifthTab = fifthTab.getText();

        System.out.println(titleOfFifthTab);

        sleep(3000);
        driver.quit();
    }

}
