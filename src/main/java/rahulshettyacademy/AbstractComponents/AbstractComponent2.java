package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CartPage2;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent2 {
	
	WebDriver driver;
	
	// CONSTRUCTOR
	public AbstractComponent2(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(css = "[routerlink*='cart']") 
	WebElement cartButton;
	
	
	public void waitElementsToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitElementsToDisappear(WebElement we) throws InterruptedException {
		
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(we));
	}
	

	public CartPage2 openCart() {
		cartButton.click(); 
		return new CartPage2(driver);
	}
	

}
