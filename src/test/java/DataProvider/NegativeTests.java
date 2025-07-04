package com.hotline.tests.scenarios;

import com.hotline.tests.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests extends BaseTest {

    @Test(groups = {"negative"})
    public void testSearchWithNonExistentProduct() {
        System.out.println("Running testSearchWithNonExistentProduct (negative test)");
        String nonExistentSearchTerm = "НесуществующийПродукт123456789";
        WebElement searchInput = driver.findElement(By.id("search-input"));
        searchInput.sendKeys(nonExistentSearchTerm);
        searchInput.sendKeys(Keys.ENTER);

        // Ожидаем, что появится сообщение об отсутствии результатов или похожий текст
        // Используем try-catch для проверки отсутствия элемента, так как NoSuchElementException
        // будет брошен, если элемент не найден, и это будет ожидаемым поведением для негативного теста.
        boolean noResultsFound = false;
        try {
            WebElement noResultsMessage = driver.findElement(By.xpath("//div[contains(@class, 'search-results__not-found')]"));
            // Или другой локатор, который указывает на сообщение об отсутствии результатов
            noResultsFound = noResultsMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            // Это ожидаемое поведение, если нет элемента, который подтверждает "нет результатов"
            // В зависимости от реализации сайта, может быть другая логика
            System.out.println("Сообщение об отсутствии результатов не найдено, возможно, другой механизм.");
        }

        // Альтернативная проверка: убедиться, что список товаров пуст или нет заголовка с названием продукта
        // Можно также проверить наличие текста "По вашему запросу ничего не найдено"
        Assert.assertTrue(driver.getPageSource().contains("ничего не найдено") || noResultsFound,
                "Сообщение об отсутствии результатов поиска не отображается или содержит неожиданный контент.");
    }
}