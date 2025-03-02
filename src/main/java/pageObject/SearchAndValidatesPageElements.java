package pageObject;

public interface SearchAndValidatesPageElements {
	String searchBox="//input[@id='twotabsearchtextbox']";
	String SearchButton="//input[@id='nav-search-submit-button']";
	String Product="//div[@class='a-section a-spacing-base']";
	String BrandName="//span[@class='a-size-base a-color-base'][normalize-space()='insta360']";
	String AddToCartProduct1="//button[@id='a-autoid-1-announce']";
	String AddToCartProduct2="//button[@id='a-autoid-2-announce']";
	String ProductPrice="//div[@role='list']";
	String ProductURL="//div[@class='a-section a-spacing-base']";
	String SearchResult="//h2[@aria-label='insta360 Link 2 - The AI-powerd 4K Webcam']";
	String GoToCart="//span[@class='nav-cart-icon nav-sprite']";
	String subtotalPrice="(//span[contains(@class,'a-size-medium a-color-base')])[2]";
	String product1Price="//span[text()='15,990']";
	String product2Price="//span[text()='24,990']";
	String deleteProduct1="(//input[@data-action='delete'])[1]";
	String deleteProduct2="//input[@data-action='delete']";
	String GoTOCart2="(//span[@class='nav-cart-icon nav-sprite'])[1]";
			
	

}
