package selenium.tutorial.studies;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SingleClickHandlingTest {

	
	WebDriver driver;
	/**
	 * Will be executed before all methods annotated with @Test. 
	 * We can give any method name but must be annotated with @BeoreTest if we want these codes to be executed before test case scenarions
	 * Usually all initializing parameters like driver initialization and common weburl if being used in all testcase methods in the same class are defined here
	 * @throws IOException
	 */
	@BeforeTest(enabled=true)
	public void setup() throws IOException {
		InputStream input=SampleTest.class.getClassLoader().getResourceAsStream("testData.properties");
		Properties property=new Properties();
		property.load(input);
		
		if("chrome".equalsIgnoreCase(property.getProperty("activeBrowser"))) {
		
			WebDriverManager.chromedriver().version(property.getProperty("driverVersion")).setup();
			driver=new ChromeDriver(); 
			//driver=new HtmlUnitDriver();
		
		} else if("firefox".equalsIgnoreCase(property.getProperty("activeBrowser"))) {
			//Add mozilla specific driver and initialize the driver
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		
		driver.get(property.getProperty("usermgmtAppURL")); 
	}
	
	/**
	 * Actual Testcase script for a particular scenario is written here.
	 * We can create multiple methods annotated with @Test if there are multiple scenarios too be executed.
	 * @throws InterruptedException 
	 */
	@Test(enabled=true)
	public void testSingleClickHandling() throws InterruptedException {
		driver.findElement(By.name("name")).sendKeys("Lalatendu");
		 driver.findElement(By.xpath("//button[text()='Click here to get profession']")).click();
		WebElement webelement = driver.findElement(By.id("profession"));
		String profession = webelement.getAttribute("value");
	    Assert.assertEquals("Java Programmer",profession);
	}
	
	/**
	 * This method will be called after all testcase executions are over.
	 * We can give any method name but must be annotated with @afterTest if we want to perform some operations ater testcase executions.
	 * Usually all closing operations like driver.close(), databaeconnection.close()if using in testcases are being done here
	 * driver.close() here will automatically close the new browser opened during testcase execution.
	 */
	@AfterTest(enabled=true)
	public void testTearDown() {
	   driver.close();
	}
}
