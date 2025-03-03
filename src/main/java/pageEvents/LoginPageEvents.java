package pageEvents;

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

public class LoginPageEvents {
	ElementFetch ele = new ElementFetch();
	WebDriver driver;

	public void verifyIfLoginPageIsLoaded() {
		Assert.assertTrue(ele.getWebElements("XPATH", LoginPageElements.continueBtn).size() > 0, "Element not found");

	}

	public void enterCredentials() throws InterruptedException {

		Thread.sleep(3000);
		ele.getWebElement("XPATH", LoginPageElements.mobileNumber).sendKeys("6352901777");
		Thread.sleep(3000);
		ele.getWebElement("XPATH", LoginPageElements.continueBtn).click();
		ele.getWebElement("XPATH", LoginPageElements.passwordField).sendKeys("Sagar@123");
		ele.getWebElement("XPATH", LoginPageElements.submitPassword).click();
		Thread.sleep(2000);
		if (ele.getWebElement("XPATH", HomePageElements.amzonLogo).isDisplayed()) {
			System.out.println("Amazon.sg logo is displayed correctly After Login.");
		} else {
			System.out.println("Amazon.sg logo is NOT displayed! After Login");
			
		}
		Thread.sleep(2000);
		Assert.assertTrue(ele.getWebElement("XPATH", HomePageElements.amzonLogo).isDisplayed(),
				"Amazon logo is NOT displayed! Before Login");

		String actualText = ele.getWebElements("XPATH", LoginPageElements.hellowUser).get(0).getText().trim();
		System.out.println("Actual Greeting Text: " + actualText);
		Assert.assertTrue(actualText.toLowerCase().contains("hello"),
				"Login greeting text does not contain 'Hello'. Actual: " + actualText);

	}

	public void verifyNavigationBar() throws InterruptedException {

// Verify Search Bar
        Thread.sleep(3000);
		WebElement searchBar = ele.getWebElement("XPATH", LoginPageElements.searchBar);
		Assert.assertTrue(searchBar.isDisplayed(), "Search bar is missing after login!");
		System.out.println("Search bar is displayed correctly after login.");

// Verify Cart Icon

		WebElement cartIcon = ele.getWebElement("XPATH", LoginPageElements.cartIcon);
		Assert.assertTrue(cartIcon.isDisplayed(), "Cart icon is missing after login!");
		System.out.println("Cart icon is displayed correctly after login.");

// Verify Retrun Order Icon

		WebElement RetrungOrder = ele.getWebElement("XPATH", LoginPageElements.returnOrder);
		Assert.assertTrue(RetrungOrder.isDisplayed(), "Cart icon is missing after login!");
		System.out.println("Return And Order  icon is displayed correctly after login.");

// Verify Navigation Bar
        Thread.sleep(3000);
		WebElement NavBar = ele.getWebElement("XPATH", LoginPageElements.NavBar);
		Assert.assertTrue(NavBar.isDisplayed(), "Navigation Bar is missing after login!");
		System.out.println("Navigation is displayed correctly after login.");

	}

}
