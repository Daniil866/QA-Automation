package hw_6.data_provider;

import org.testng.annotations.DataProvider;

public class SearchTestData {

    @DataProvider(name = "getSearchData")
    public Object[][] getSearchData() {
        return new Object[][]{
                {"iPhone", "Apple iPhone (Айфони)"},
                {"iPad", "iPad 2025, Лінійка iPad 2025"},
                {"xbox", "Ігрові приставки Microsoft"}
        };
    }

}
