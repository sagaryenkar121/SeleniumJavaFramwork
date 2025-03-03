package pageEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.SearchAndValidatesPageElements;
import utils.ElementFetch;
import utils.ScrollToViwe;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SearchAndAddToCartEvents {
	WebDriver driver;
	private WebDriverWait wait;
	ElementFetch ele = new ElementFetch();

	public void searchAndValidateProduct() throws InterruptedException {

		Thread.sleep(5000);
		ele.getWebElement("XPATH", SearchAndValidatesPageElements.searchBox).sendKeys("insta360 link 2");
		ele.getWebElement("XPATH", SearchAndValidatesPageElements.SearchButton).click();
		ele.getWebElement("XPATH", SearchAndValidatesPageElements.BrandName).click();

		List<WebElement> products = ele.getWebElements("XPATH", SearchAndValidatesPageElements.Product);
		Assert.assertTrue(products.size() > 0, "No products found!");

	}

	public void verifySearchResult(String expectedKeyword) {
		// Get the list of all search result elements
		List<WebElement> products1 = ele.getWebElements("XPATH", SearchAndValidatesPageElements.SearchResult);

		// Assert that there are products found
		Assert.assertTrue(products1.size() > 0, "No products found!");

		boolean keywordFound = false;

		// Iterate through each product title and check if it contains the expected
		// keyword
		for (WebElement product : products1) {
			String productText = product.getText().toLowerCase();
			System.out.println("Product Found: " + productText);

			if (productText.contains(expectedKeyword.toLowerCase())) {
				keywordFound = true;
			}
		}

		// Assert that at least one search result contains the expected keyword
		Assert.assertTrue(keywordFound, "No product in search results contains the keyword: " + expectedKeyword);
	}

	public void addTocartAndCheckout() throws InterruptedException {
		Thread.sleep(2000);
		ele.getWebElement("XPATH", SearchAndValidatesPageElements.AddToCartProduct1).click();
		Thread.sleep(2000);
		ele.getWebElement("XPATH", SearchAndValidatesPageElements.AddToCartProduct2).click();
		Thread.sleep(2000);
		ele.getWebElement("XPATH", SearchAndValidatesPageElements.GoToCart).click();
	}

	public void verifyCartSubtotal() throws InterruptedException {
		Thread.sleep(5000);
		// Get Product 1 Price
		WebElement product1Element = ele.getWebElement("XPATH", SearchAndValidatesPageElements.product1Price);
		double product1Price = extractPrice(product1Element.getText());

		// Get Product 2 Price
		WebElement product2Element = ele.getWebElement("XPATH", SearchAndValidatesPageElements.product2Price);
		double product2Price = extractPrice(product2Element.getText());

		// Calculate Expected Subtotal
		double expectedSubtotal = product1Price + product2Price;

		// Get Actual Subtotal from Cart
		WebElement subtotalElement = ele.getWebElement("XPATH", SearchAndValidatesPageElements.subtotalPrice);
		double actualSubtotal = extractPrice(subtotalElement.getText());

		// Print product prices and subtotal
		System.out.println("🔹 Product 1 Price: ₹" + product1Price);
		System.out.println("🔹 Product 2 Price: ₹" + product2Price);
		System.out.println("🔹 Expected Subtotal (Product 1 + Product 2): ₹" + expectedSubtotal);
		System.out.println("🔹 Actual Subtotal from Cart: ₹" + actualSubtotal);

		// Assert that the calculated subtotal matches the actual subtotal
		Assert.assertEquals(actualSubtotal, expectedSubtotal,
				"❌ Subtotal mismatch! The sum of product prices does not match.");
	}

	// Empty cart
	public void removeItemsFromCart() throws InterruptedException {

		Thread.sleep(5000);
		ele.getWebElement("XPATH", SearchAndValidatesPageElements.GoTOCart2).click();
		//Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 WebElement deleteProduct1 = wait.until(ExpectedConditions.elementToBeClickable(
		            ele.getWebElement("XPATH", SearchAndValidatesPageElements.deleteProduct1)));
		    deleteProduct1.click();
		    System.out.println("✅ Clicked 'Delete Product 1'.");
		//ele.getWebElement("XPATH", SearchAndValidatesPageElements.deleteProduct1).click();
		Thread.sleep(5000);
		ele.getWebElement("XPATH", SearchAndValidatesPageElements.deleteProduct2).click();
		System.out.println("✅ Clicked 'Delete Product 2'.");

	}

	// Helper method to extract numerical value from price string
	private double extractPrice(String priceText) {
		return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
	}
}
