package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest2 {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/MSI/Documents/chromedriver-win64-130/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBinary("C:/Users/MSI/Downloads/chrome-win64 (1)/chrome-win64/chrome.exe");
		//chromeOptions.addArguments("user-data-dir=C:/Users/MSI/AppData/Local/Google/Chrome for Testing/User Data");
		
		WebDriver driver = new ChromeDriver(chromeOptions);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		
		// Retrieve all cookies and print their names
        for (Cookie cookie : driver.manage().getCookies()) {
            System.out.println("Cookie Name: " + cookie.getName());
            System.out.println("Cookie Value: " + cookie.getValue());
            System.out.println("Cookie Expiry: " + cookie.getExpiry());
            System.out.println("Is HttpOnly: " + cookie.isHttpOnly());
            System.out.println("Is Secure: " + cookie.isSecure());
            System.out.println("-----------");
        }

		driver.findElement(By.id("userEmail")).sendKeys("anilh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("IAmThePass.1");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		String productName = "ZARA COAT 3";
		
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
		WebElement first_item = items.stream()
			.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("QWERTY"))
			.findFirst().orElse(null);
		
		driver.findElement(By.cssSelector("body > app-root:nth-child(1) > app-dashboard:nth-child(2) > section:nth-child(5) > "
				+ "div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > button:nth-child(4)")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
		
		WebElement second_item = items.stream()
			.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
			.findFirst().orElse(null);
		
		// Print out element information to debug
        System.out.println("Tag Name: " + first_item.getTagName());
        System.out.println("Text: " + first_item.getText());
        System.out.println("Location: " + first_item.getLocation());
        System.out.println("Size: " + first_item.getSize());
        System.out.println("Displayed: " + first_item.isDisplayed());
        System.out.println("Enabled: " + first_item.isEnabled());
        System.out.println("Selected: " + first_item.isSelected());
        System.out.println("-------");
        
        
		second_item.findElement(By.cssSelector(".card-body button:last-child")).click();
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
		
		driver.findElement(By.xpath("(//button[@routerlink='/dashboard/cart'])")).click();
		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartList = driver.findElements(By.cssSelector(".cartWrap"));
		
		Boolean zaraCoat = cartList.stream().anyMatch(name -> name.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName));
		
		
		//Boolean zaraCoat = driver.findElement(By.xpath("//h3[normalize-space()='ZARA COAT 3']")).getText().contains(productName);
		Assert.assertTrue(zaraCoat);
		
		// How to get rid of sleep? 
		Thread.sleep(500);
		//driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		//driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		
		WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkout);
		
		
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ta-results")));
		
		
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		WebElement indiaClick = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		js.executeScript("arguments[0].click();", indiaClick);
		
		System.out.println(0);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("action__submit")));
		System.out.println(1);
		//driver.findElement(By.className("action__submit")).click();
		WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
		js.executeScript("arguments[0].click();", placeOrder);

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
		
		
		
		
		
		
		

	}

}
