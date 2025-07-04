package hw_3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;

public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.olx.ua/uk/");

        WebElement searchField = driver.findElement(By.cssSelector("[name='search']"));
        searchField.sendKeys("Samsung", Keys.ENTER); // Предмет поиска изменен на "Samsung"

        sleep(3000);

        int maxPrice = driver.findElements(By.cssSelector("div.price")).stream()
                .map(webElement -> parseInt(webElement.getText().replace("₴", "").replace(" ", "")))
                .max(Integer::compare)
                .get();

        List<WebElement> priceElements = driver.findElements(By.cssSelector("div.price"));
        List<Integer> priceTexts = new ArrayList<>();
        for(int i = 0; i < priceElements.size(); i++) {
            String price = priceElements.get(i).getText();
            price = price.replace("₴", "").replace(" ", "");
            int priceInt = parseInt(price);
            priceTexts.add(priceInt);
        }

        int maxPriceResult = 0;

        for(int val : priceTexts) {
            if(maxPriceResult < val) {
                maxPriceResult = val;
            }
        }

        System.out.println(maxPriceResult);
        System.out.println(maxPrice);

        driver.quit();
    }

}