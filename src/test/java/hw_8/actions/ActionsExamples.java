package hw_8.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionsExamples {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        WebElement element = driver.findElement(By.cssSelector(""));
        WebElement source = driver.findElement(By.cssSelector(""));
        WebElement target = driver.findElement(By.cssSelector(""));

        //Наводить курсор миші на певний елемент.
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

        //Здійснює подвійний клік в поточній позиції миші.
        actions.doubleClick().perform();
        actions.doubleClick(element).perform();

        //Виконує "правий клік" (контекстне меню)в поточній позиції миші.
        actions.contextClick().perform();
        actions.contextClick(element).perform();

        //Перетягує елемент source і кидає його на інший елемент target.
        actions.dragAndDrop(source, target).perform();

//        Утримання клавіші SHIFT та введення тексту великими літерами:
        actions.keyDown(Keys.SHIFT).sendKeys("hello").keyUp(Keys.SHIFT).perform();

//        Послідовність дій (наведення, очікування, клік):
        actions.moveToElement(element).pause(Duration.ofSeconds(2)).click().perform();
    }

}
