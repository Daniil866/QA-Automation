import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class XPathTests extends HotlineBaseTest {

    // Тест 1: Проверка перехода на главную страницу по логотипу
    @Test
    public void test1_navigateHomeByLogoXPath() {
        // Переходим на страницу, отличную от главной (например, на страницу поиска, если возможно)
        driver.get("https://hotline.ua/mobile/");
        wait.until(ExpectedConditions.urlContains("mobile"));

        // Кликаем по логотипу Hotline.ua, используя XPath
        // XPath для логотипа может выглядеть так: //a[@class='logo-container'] или //a[contains(@class, 'logo-container')]
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'logo-container')]")));
        logo.click();

        // Проверяем, что URL вернулся на главную страницу
        wait.until(ExpectedConditions.urlToBe("https://hotline.ua/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://hotline.ua/", "Failed to navigate to home page via logo.");
        System.out.println("Test 1 (XPath): Navigated to home page by logo successfully.");
    }

    // Тест 2: Поиск продукта по XPath
    @Test
    public void test2_searchProductByXPath() {
        String searchTerm = "Samsung Galaxy S25 Ultra 12/256GB Titanium Black (SM-S938BZKD)";

        // XPath для поля поиска: //input[@id='search'] или //input[@placeholder='пошук']
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search']")));
        searchInput.sendKeys(searchTerm);

        // XPath для кнопки поиска: //button[contains(@class, 'search-button')]
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'search-button')]")));
        searchButton.click();

        // Проверка URL
        String expectedUrlPart = searchTerm.replace(" ", "+").toLowerCase();
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(expectedUrlPart), "Search results URL does not contain search term.");

        // Проверка наличия результатов (поиск части названия продукта)
        // XPath для элемента, содержащего часть названия продукта: //*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'galaxy s25 ultra')]
        WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'galaxy s25 ultra')]")));
        Assert.assertTrue(resultElement.isDisplayed(), "Search results for 'Galaxy S25 Ultra' not found.");
        System.out.println("Test 2 (XPath): Product search completed successfully.");
    }

    // Тест 3: Навигация по категории (например, "Мобильные телефоны") с использованием XPath
    @Test
    public void test3_navigateToCategoryByXPath() {
        // XPath для кнопки "Каталог товаров"
        WebElement catalogButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='header-menu-label' and text()='Каталог товарів']")));
        catalogButton.click();

        // XPath для пункта меню "Мобильные телефоны" (или любой другой категории)
        // XPath может быть сложным, но давайте попробуем что-то вроде:
        // //a[contains(@class, 'menu-item') and contains(text(), 'Мобільні телефони')]
        WebElement mobilePhonesLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'menu-item') and contains(text(), 'Мобільні телефоны')]")));
        mobilePhonesLink.click();

        // Проверка, что URL изменился на страницу мобильных телефонов
        wait.until(ExpectedConditions.urlContains("mobile"));
        Assert.assertTrue(driver.getCurrentUrl().contains("mobile"), "Failed to navigate to Mobile Phones category.");
        System.out.println("Test 3 (XPath): Navigated to Mobile Phones category successfully.");
    }
}