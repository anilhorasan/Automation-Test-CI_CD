package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.AbstractComponents.AbstractComponent2;

public class ProductCatalogue2 extends AbstractComponent2{

	WebDriver driver;

	public ProductCatalogue2(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement addedDisappear;

	By productsBy = By.cssSelector(".mb-3");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	By addedAppearBy = By.cssSelector("#toast-container");
	
	public List<WebElement>  getProductList() {
		waitElementsToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);	
		return prod;
	}
	
	public void addToCart(String productName) throws InterruptedException {
		getProductByName(productName).findElement(addToCartBy).click();
		waitElementsToAppear(addedAppearBy);
		waitElementsToDisappear(addedDisappear);
		
	}
	
	

}
