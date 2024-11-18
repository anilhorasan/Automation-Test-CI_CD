package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.AbstractComponents.AbstractComponent2;

public class CartPage2 extends AbstractComponent2 {
	WebDriver driver;
	
	public CartPage2(WebDriver driver) {
		super(driver);
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> products;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;
	
	public List<WebElement> getOrderList() {
		return products;
	}
	
	public boolean controlOrder(String productName) {
		Boolean match = getOrderList().stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage2 clickCheckout() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkoutButton);
		return new CheckoutPage2(driver);
	}

	

	


}
