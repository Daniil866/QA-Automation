package hw_9;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import static hw_9.TestRailController.publishData;

public class BaseTest {

    protected int testCaseId;

//    @BeforeMethod
//    public void openBrowser() {
//        Selenide.open("https://hotline.ua/");
//    }

    @AfterMethod
    public void publishResultsToTestRail(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            publishData(1, testCaseId);
            System.out.println("Publish success result to TestRail for test id: " + testCaseId);
        } else {
            publishData(5, testCaseId);
            System.out.println("Publish failed result to TestRail for test id: " + testCaseId);
        }
    }

}
