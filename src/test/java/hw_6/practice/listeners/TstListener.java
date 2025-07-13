package hw_6.practice.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TstListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Error in test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test success: " + result.getName());
    }
}