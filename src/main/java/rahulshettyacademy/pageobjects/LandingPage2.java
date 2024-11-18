package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.AbstractComponents.AbstractComponent2;

public class LandingPage2 extends AbstractComponent2{

	WebDriver driver;

	public LandingPage2(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmails = driver.findElement(By.id("userEmail"));
	// PageFactory

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement login;

	public void goTo() {
		// GO TO THE WEBSITE for testing
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalogue2 loginApplication(String email, String password) {
		// Enter Credentials to login
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		return new ProductCatalogue2(driver);
	}

}
