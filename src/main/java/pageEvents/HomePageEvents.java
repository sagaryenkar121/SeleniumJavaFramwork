package pageEvents;
import org.testng.Assert;
import pageObject.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents {
	ElementFetch ele= new ElementFetch();
	public void signInButton() throws InterruptedException {
		//Thread.sleep(5000);
		if(ele.getWebElement("XPATH", HomePageElements.amzonLogo).isDisplayed()) {
			System.out.println("Amazon.sg logo is displayed correctly Before Login.");
		}else {
			 System.out.println("Amazon.sg logo is NOT displayed! BeforeLogin");
			 
		}
		//Thread.sleep(5000);
		Assert.assertTrue(ele.getWebElement("XPATH", HomePageElements.amzonLogo).isDisplayed(), "Amazon logo is NOT displayed! Before Login");
		
		//ele.getWebElement("XPATH", HomePageElements.signInButtonText).click();
		
		
		
		
		
	}

}
