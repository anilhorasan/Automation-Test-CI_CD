package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;


	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement india;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		country.sendKeys(countryName);
		waitForElementToAppear(results);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", india);
	}
	
	

	By autosuggestionResultsBy = By.cssSelector(".ta-results");

	public void enterCountry(String countryInitials) {
		country.sendKeys(countryInitials);
		waitForElementToAppear(autosuggestionResultsBy);
	}

	public void clickToCountry() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", india);
	}
	
	public ConfirmationPage submitOrder()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", placeOrderButton);
		return new ConfirmationPage(driver);
		
		
	}

}
