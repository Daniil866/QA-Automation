package HW_2;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class HotlineSearchTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        try {
            String keyWordToFind = "iPhone 15";

            driver.manage().window().maximize();
            driver.get("https://hotline.ua/");

            WebElement searchField = driver.findElement(By.xpath("//*[@id=\"autosuggest\"]/div[1]/input"));
            //WebElement searchField = driver.findElement(By.("[name='search']"));
            searchField.sendKeys(keyWordToFind);

            WebElement searchButton = driver.findElement(By.cssSelector("[class*='search__btn']"));
            searchButton.click();

            sleep(3000);

            //WebElement iPhonePageTitle = driver.findElement(By.className("[class='list-item']"));
            WebElement iPhonePageTitle = driver.findElement(By.cssSelector("div[class^='search-list']"));
            String titleText = iPhonePageTitle.getText();

            Assert.assertTrue("Title doesn't contains " + keyWordToFind + " word.", titleText.contains(keyWordToFind));
        } catch (AssertionError ex) {
            ex.printStackTrace();
        } finally {
            driver.quit();
        }

    }

}

