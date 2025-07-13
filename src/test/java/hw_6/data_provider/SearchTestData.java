package data_provider;

import org.testng.annotations.DataProvider;

public class SearchTestData {

    @DataProvider
    public Object[][] getSearchData() {
        return new Object[][]{
                {"iPhone", "Apple iPhone"},
                {"iPad", "Apple iPad"},
                {"xbox", "Ігрові приставки Microsoft"}
        };
    }

}
