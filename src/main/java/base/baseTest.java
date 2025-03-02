package base;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

public class baseTest {
	public static WebDriver driver;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@BeforeTest
	public void beforeTestMethod() {
		sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + File.separator + "reports" + File.separator + "TestExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Automation Test Result By Sagar Yenkar");
	}

	@BeforeMethod
	@Parameters({"browser", "headless", "mobileEmulation"})
	public void BeforeMethodMethod(String browser, String headless, String mobileEmulation, Method testMethod) {
	    logger = extent.createTest(testMethod.getName());
	    String description = testMethod.getAnnotation(Test.class).description();
	    if (!description.isEmpty()) {
	        logger.log(Status.INFO, "**Test Description:** " + description);
	    }

	    boolean isHeadless = Boolean.parseBoolean(headless);
	    boolean isMobile = Boolean.parseBoolean(mobileEmulation);

	    setupDriver(browser, isHeadless, isMobile);
	    driver.manage().window().maximize();
	    driver.get(Constants.url);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterMethod
	public void afterTestMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "- Test case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + "- Test case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + "- Test case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "- Test case PASS", ExtentColor.GREEN));
		}
		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		extent.flush();
	}

	public void setupDriver(String browser, boolean isHeadless, boolean isMobile) {
	    if (browser.equalsIgnoreCase("chrome")) {
	        WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--remote-allow-origins=*");

	        if (isHeadless) {
	            options.addArguments("--headless", "--disable-gpu");
	            if (isMobile) {
	                Map<String, String> mobileEmulation = new HashMap<>();
	                mobileEmulation.put("deviceName", "iPhone X");
	                options.setExperimentalOption("mobileEmulation", mobileEmulation);
	            }
	        }

	        driver = new ChromeDriver(options);
	    } 
	    else if (browser.equalsIgnoreCase("firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        FirefoxOptions options = new FirefoxOptions();

	        if (isHeadless) {
	            options.addArguments("--headless");
	            if (isMobile) {
	                options.addArguments("--width=375", "--height=812"); // iPhone X resolution
	            }
	        }

	        driver = new FirefoxDriver(options);
	    } 
	    else if (browser.equalsIgnoreCase("edge")) {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
	    } 
	    else {
	        throw new IllegalArgumentException("Unsupported browser: " + browser);
	    }

	    clearCookiesAndCache();
	}

	private void clearCookiesAndCache() {
		try {
			driver.manage().deleteAllCookies(); // Clears cookies
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.localStorage.clear(); window.sessionStorage.clear();"); // Clears local & session
																								// storage
			System.out.println("Cookies and cache cleared successfully.");
		} catch (Exception e) {
			System.out.println("Error clearing cookies and cache: " + e.getMessage());
		}
	}
}
