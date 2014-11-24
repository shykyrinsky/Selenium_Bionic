package utils;

import functional.FunctionalTest;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by Illya on 21.11.2014.
 */
public class FunctionalTestRule extends TestListenerAdapter {


    @Override
    public void onTestStart(ITestResult testResult) {
        Log4Test.info(String.format("-------%s-------", testResult.getName()));
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        String methodName = testResult.getMethod().getMethodName();
        Log4Test.result("FAILED!...Capturing Screenshot...");
        new ScreenshotMaker(FunctionalTest.driver).takeScreenShot(methodName);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        Log4Test.result("PASSED!");
    }



}
