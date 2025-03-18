package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.baseTest;

public class SuiteListener implements ITestListener, IAnnotationTransformer{
	
	@Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result, "failscreenshot");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result, "passscreenshot");
    }

    private void takeScreenshot(ITestResult result, String folderName) {
        String methodName = result.getMethod().getMethodName();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String dirPath = System.getProperty("user.dir") + File.separator + folderName;
        
        // Create directory if it doesn't exist
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = dirPath + File.separator + methodName + "_" + timestamp + ".png";

        File screenshot = ((TakesScreenshot) baseTest.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/*
	 * public void transform(ITestAnnotation annotation, Class testClass,
	 * Constructor testConstructor, Method testMethod) {
	 * annotation.setRetryAnalyzer(RetryAnalyzer.class);
	 * 
	 * }
	 */

}
