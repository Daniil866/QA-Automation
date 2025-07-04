package com.hotline.tests.scenarios;

import com.hotline.tests.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTestsOne extends BaseTest {

    @Test(groups = {"positive"})
    public void testHomePageTitle() {
        System.out.println("Running testHomePageTitle");
        String expectedTitle = "Hotline — сравнить цены в интернет-магазинах Украины, купить товары";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Заголовок страницы не совпадает");
    }

    @Test(groups = {"positive"})
    public void testSearchFunctionality() {
        System.out.println("Running testSearchFunctionality");
        String searchTerm = "Samsung Galaxy S24";
        WebElement searchInput = driver.findElement(By.id("search-input"));
        searchInput.sendKeys(searchTerm);
        searchInput.sendKeys(Keys.ENTER);

        // Проверяем, что результаты поиска отображаются
        WebElement searchResultsHeader = driver.findElement(By.xpath("//h1[contains(text(), '" + searchTerm + "')]"));
        Assert.assertTrue(searchResultsHeader.isDisplayed(), "Заголовок результатов поиска не отображается");
    }
}