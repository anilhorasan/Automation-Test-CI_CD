package rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CartPage2;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.CheckoutPage2;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ConfirmationPage2;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.LandingPage2;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.ProductCatalogue2;

public class SubmitOrderTest2 {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		String email = "anilh@gmail.com";
		String password = "IAmThePass.1";
		String successMessage = "THANKYOU FOR THE ORDER.";
		System.setProperty("webdriver.chrome.driver", "C:/Users/MSI/Documents/chromedriver-win64-130/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBinary("C:/Users/MSI/Downloads/chrome-win64 (1)/chrome-win64/chrome.exe");
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();

		// GO TO Landing Page, login with credentials.
		LandingPage2 landingPage = new LandingPage2(driver);
		landingPage.goTo();
		ProductCatalogue2 products = landingPage.loginApplication(email, password);

		// ADD ITEM to the cart and go to cart page
		products.addToCart(productName);
		CartPage2 cartPage = products.openCart();
		
		// Check if the item is in the cart and then proceed to next page
		Assert.assertTrue(((CartPage2) cartPage).controlOrder(productName));
		CheckoutPage2  checkoutPage = cartPage.clickCheckout();
		
		// Fill the country and proceed to finish
		checkoutPage.enterCountry("ind");
		checkoutPage.clickToCountry();
		ConfirmationPage2 confirmationPage = checkoutPage.placeOrder();
		System.out.println(successMessage);
		System.out.println(confirmationPage.getMessage());
		Assert.assertEquals(successMessage, confirmationPage.getMessage());

		//driver.close();

	}

}
