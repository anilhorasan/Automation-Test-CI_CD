package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent2;

public class CheckoutPage2 extends AbstractComponent2 {

	WebDriver driver;

	public CheckoutPage2(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement india;

	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;

	By autosuggestionResultsBy = By.cssSelector(".ta-results");

	public void enterCountry(String countryInitials) {
		country.sendKeys(countryInitials);
		waitElementsToAppear(autosuggestionResultsBy);
	}

	public void clickToCountry() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", india);
	}

	public ConfirmationPage2 placeOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
		js.executeScript("arguments[0].click();", placeOrderButton);
		return new ConfirmationPage2(driver);
	}

}
