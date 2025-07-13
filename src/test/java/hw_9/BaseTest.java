package hw_9;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Configuration;

import static hw_9.TestRailController.publishData;

public class BaseTest {

    protected int testCaseId;
    private static final String BASE_URL = "https://brain.com.ua/";

    @BeforeMethod
    public void openSite() {
        open(BASE_URL);
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
    }

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