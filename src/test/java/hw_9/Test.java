package hw_9;

import org.testng.annotations.Test;

public class Tests extends BaseTest {

    @Test
    public void verifySearchResultTitleIsCorrect() {
        testCaseId = 1;
        System.out.println("First test execution");
    }

    @Test
    public void verifyUserCanBeNavigatedToTheProductDetailsPage() {
        testCaseId = 2;
        System.out.println("Second test execution");
        System.out.println(1 / 0);
    }

    @Test
    public void verifyUserCanBeNavigatedToTheReminderPage() {
        testCaseId = 3;
        System.out.println("Third test execution");
    }

}
