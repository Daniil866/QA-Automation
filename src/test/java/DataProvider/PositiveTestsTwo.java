package com.hotline.tests.scenarios;

import com.hotline.tests.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PositiveTestsTwo extends BaseTest {

    @DataProvider(name = "productCategories")
    public Object[][] getProductCategories() {
        return new Object[][]{
                {"Мобильные телефоны"},
                {"Ноутбуки"},
                {"Телевизоры"}
        };
    }

    @Test(dataProvider = "productCategories", groups = {"positive"})
    public void testNavigateToCategory(String categoryName) {
        System.out.println("Running testNavigateToCategory with category: " + categoryName);
        WebElement categoryLink = driver.findElement(By.xpath("//a[contains(@class, 'menu-main__link') and text()='" + categoryName + "']"));
        categoryLink.click();

        WebElement categoryHeader = driver.findElement(By.xpath("//h1[contains(text(), '" + categoryName + "')]"));
        Assert.assertTrue(categoryHeader.isDisplayed(), "Заголовок категории не отображается: " + categoryName);
    }

    @Test(groups = {"positive"})
    public void testFooterLinksPresence() {
        System.out.println("Running testFooterLinksPresence");
        WebElement aboutUsLink = driver.findElement(By.xpath("//a[text()='О Hotline']"));
        WebElement contactsLink = driver.findElement(By.xpath("//a[text()='Контакты']"));

        Assert.assertTrue(aboutUsLink.isDisplayed(), "Ссылка 'О Hotline' не отображается");
        Assert.assertTrue(contactsLink.isDisplayed(), "Ссылка 'Контакты' не отображается");
    }
}