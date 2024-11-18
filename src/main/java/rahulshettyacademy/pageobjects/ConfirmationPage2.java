package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ConfirmationPage2 {
	
	WebDriver driver;
	
	public ConfirmationPage2(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement successMessage;

	By autosuggestionResultsBy = By.cssSelector(".ta-results");

	
	public String getMessage() {
		String confirmMessage = successMessage.getText();
		return confirmMessage;
	}


}
