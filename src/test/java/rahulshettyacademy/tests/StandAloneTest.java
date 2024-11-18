package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/MSI/Documents/chromedriver-win64-130/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBinary("C:/Users/MSI/Downloads/chrome-win64 (1)/chrome-win64/chrome.exe");
		WebDriver driver = new ChromeDriver(chromeOptions);
		// Choose one of them and comment out the other to test.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// GO TO THE WEBSITE for testing
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		//Why do we need this line? IDK
		//LandingPage landingPage = new LandingPage(driver);
		
		// Enter Credentials to login
		driver.findElement(By.id("userEmail")).sendKeys("anilh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("IAmThePass.1");
		driver.findElement(By.id("login")).click();
		
		// After login, wait until items are visible and clickable. Then start your tests
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// Find the item "zara coat" and add it to cart
		String productName = "ZARA COAT 3";
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //last-child also works
		
		
		// Print out element information to DEBUG
        System.out.println("Tag Name: " + prod.getTagName());
        System.out.println("Text: " + prod.getText());
        System.out.println("Location: " + prod.getLocation());
        System.out.println("Size: " + prod.getSize());
        System.out.println("Displayed: " + prod.isDisplayed());
        System.out.println("Enabled: " + prod.isEnabled());
        System.out.println("Selected: " + prod.isSelected());
        System.out.println("-------");

        
        
		// Wait for "Product added to cart" and then wait for it to disappear to click to cart.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating
		// This line caused performance issues (i.e. invisibilityOfElementLocated)
		// So commenting this line out and change it to invisibility of webdriver itself.
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//  Click and open the cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// Take the list of all the items in the cart and check if "zara coat" is there... 
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		// Wait for the checkout button to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
		
		// Click checkout... But the button is not working anymore. So use JS to click it! 
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkout);

		// In the payment section, Mandatory field --> Country
		//Actions a = new Actions(driver);
		//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "ind").build().perform();
		//instead of actions, we could directly write the code like this: 
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		
		// three results will appear in the autosuggestion dropdown. Wait for them to appear. 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		// Click the second element in the autosuggestive list: 
		// Alternative: driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		// Again, Autosuggestive dropdown "India" button is not clickable. So use JS...
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		WebElement indiaClick = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		js.executeScript("arguments[0].click();", indiaClick);
		
		// Again, "Place Order" button is not clickable. So use JS...
		//driver.findElement(By.cssSelector(".action__submit")).click();
		WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
		js.executeScript("arguments[0].click();", placeOrder);

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();

	}

}
