package hw_6.theory.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(">>>" + result.getName() + " is started");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(">>>" + context.getName() + " is finished");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(">>>" + result.getName() + " is success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(">>>" + result.getName() + " is failed");
    }

}
