package qa.tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.baseTest;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import pageEvents.SearchAndAddToCartEvents;
import utils.ElementFetch;

public class TestCase1 extends baseTest {
	ElementFetch ele = new ElementFetch();
	HomePageEvents homePage = new HomePageEvents();
	LoginPageEvents loginPage = new LoginPageEvents();
	SearchAndAddToCartEvents searchProductPage = new SearchAndAddToCartEvents();

	@Test(priority = 3, description = "Verify that the login page is loaded successfully And Login into Application.")
	public void verfyEnteringCredential() throws InterruptedException {
		logger.info("Verify the Sign In Button and login");
		homePage.signInButton();
		loginPage.verifyIfLoginPageIsLoaded();
		loginPage.enterCredentials();

	}

	@Test(priority = 2, description = "Verify that the top navigation bar appears correctly without any rendering issue or missing elements ")
	public void validateNavigationBar() throws InterruptedException {
		logger.info("Verify the navigation bar, including the username, search bar, and cart icon. ");
		loginPage.verifyNavigationBar();

	}

	@Test(priority = 1, description = "Search  for product insta360 link 2 And Add to Cart Also Verify Price of both product and subtotal.")
	public void searchProductAndAdd() throws InterruptedException {
		logger.info("Verify the searched product, validate the product, add it to the cart, and also verify the subtotal.");
		Thread.sleep(2000);
		loginPage.enterCredentials();
		searchProductPage.searchAndValidateProduct();
		searchProductPage.verifySearchResult("insta360 link 2");
		searchProductPage.addTocartAndCheckout();
		searchProductPage.verifyCartSubtotal();
	}

	@Test(priority = 4, description = "Verify the Cart is empaty after Verification ")
	public void EmptyCart() throws InterruptedException {
		logger.info("Remove all added Product from cart make sure cart is empty ");
		Thread.sleep(2000);
		loginPage.enterCredentials();
		searchProductPage.removeItemsFromCart();
	}

}
