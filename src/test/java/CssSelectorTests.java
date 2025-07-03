import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CssSelectorTests extends HotlineBaseTest {

    // Тест 4: Проверка перехода на главную страницу по логотипу
    @Test
    public void test4_navigateHomeByLogoCssSelector() {
        // Переходим на страницу, отличную от главной
        driver.get("https://hotline.ua/computer/");
        wait.until(ExpectedConditions.urlContains("computer"));

        // Кликаем по логотипу Hotline.ua, используя CSS-селектор
        // CSS-селектор для логотипа: a.logo-container
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.logo-container")));
        logo.click();

        // Проверяем, что URL вернулся на главную страницу
        wait.until(ExpectedConditions.urlToBe("https://hotline.ua/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://hotline.ua/", "Failed to navigate to home page via logo.");
        System.out.println("Test 4 (CSS): Navigated to home page by logo successfully.");
    }

    // Тест 5: Поиск продукта по CSS-селектору
    @Test
    public void test5_searchProductByCssSelector() {
        String searchTerm = "Apple iPhone 15 Pro Max 256GB Natural Titanium";

        // CSS-селектор для поля поиска: #search
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#search")));
        searchInput.sendKeys(searchTerm);

        // CSS-селектор для кнопки поиска: button.search-button.icon-search
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.search-button.icon-search")));
        searchButton.click();

        // Проверка URL
        String expectedUrlPart = searchTerm.replace(" ", "+").toLowerCase();
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(expectedUrlPart), "Search results URL does not contain search term.");

        // Проверка наличия результатов (поиск части названия продукта)
        // CSS-селектор для элемента, содержащего часть названия продукта.
        // Это может быть сложнее с CSS, если текст не в атрибуте.
        // Придется искать по классу/тегу и проверять текст.
        // Пример (может потребовать адаптации под актуальную разметку сайта):
        // Если элементы результатов имеют класс .item-title или .product-name
        WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".product-title a[title*='iPhone 15 Pro Max'], .g-i-tile-title a[title*='iPhone 15 Pro Max']")));
        Assert.assertTrue(resultElement.isDisplayed(), "Search results for 'iPhone 15 Pro Max' not found.");
        System.out.println("Test 5 (CSS): Product search completed successfully.");
    }

    // Тест 6: Навигация по категории (например, "Ноутбуки") с использованием CSS-селектора
    @Test
    public void test6_navigateToCategoryByCssSelector() {
        // CSS-селектор для кнопки "Каталог товаров"
        WebElement catalogButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header-menu-label")));
        catalogButton.click();

        // CSS-селектор для пункта меню "Ноутбуки" (или любой другой категории)
        // Пример (нужно адаптировать под реальный HTML-код):
        // li.main-menu-item a[title*='Ноутбуки']
        WebElement laptopsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.main-menu-item a[title*='Ноутбуки']")));
        laptopsLink.click();

        // Проверка, что URL изменился на страницу ноутбуков
        wait.until(ExpectedConditions.urlContains("notebook"));
        Assert.assertTrue(driver.getCurrentUrl().contains("notebook"), "Failed to navigate to Laptops category.");
        System.out.println("Test 6 (CSS): Navigated to Laptops category successfully.");
    }
}