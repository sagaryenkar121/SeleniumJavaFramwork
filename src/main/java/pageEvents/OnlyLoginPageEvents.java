package pageEvents;

import org.openqa.selenium.WebDriver;

import pageObject.LoginPageElements;
import pageObject.OnlyLoginPageElements;
import utils.ElementFetch;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObject.HomePageElements;
import pageObject.LoginPageElements;
import utils.ElementFetch;

public class OnlyLoginPageEvents {
	ElementFetch ele = new ElementFetch();
	WebDriver driver;

	public void OnlyLogin() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        // Wait for Sign In button to be clickable and click it
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                ele.getWebElement("XPATH", OnlyLoginPageElements.SignInButton1)));
        
        signInButton.click();
        //ele.getWebElement("XPATH", OnlyLoginPageElements.SignInButton1).click();
		ele.getWebElement("XPATH", OnlyLoginPageElements.mobileNumber1).sendKeys("6352901777");
		Thread.sleep(3000);
		ele.getWebElement("XPATH", OnlyLoginPageElements.continueBtn1).click();
		ele.getWebElement("XPATH", OnlyLoginPageElements.passwordField1).sendKeys("Sagar@123");
		ele.getWebElement("XPATH", OnlyLoginPageElements.submitPassword1).click();

	}
}
