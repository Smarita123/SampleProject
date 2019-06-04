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

public class HTMLUnitDriverTest {

	
	WebDriver driver;
	/**
	 * Will be executed before all methods annotated with @Test. 
	 * We can give any method name but must be annotated with @BeoreTest if we want these codes to be executed before test case scenarions
	 * Usually all initializing parameters like driver initialization and common weburl if being used in all testcase methods in the same class are defined here
	 * @throws IOException
	 */
	@BeforeTest(enabled=true)
	public void setup() throws IOException {
		InputStream input=HTMLUnitDriverTest.class.getClassLoader().getResourceAsStream("testData.properties");
		Properties property=new Properties();
		property.load(input);
		
		
	if("HTMLUnitDriver".equalsIgnoreCase(property.getProperty("activeBrowser"))) {
			//Add HTMLUnitDriver and initialize the driver
			
			driver=new HtmlUnitDriver();
			((HtmlUnitDriver)driver).setJavascriptEnabled(true);
			//No need of Window maximise and timeout as no window will be opened
			
		}
		
		driver.get(property.getProperty("usermgmtAppURL")); 
	}
	
	/**
	 * Actual Testcase script for a particular scenario is written here.
	 * We can create multiple methods annotated with @Test if there are multiple scenarios too be executed.
	 * @throws InterruptedException 
	 */
	@Test(enabled=true)
	public void testHtmlUnitDriverAsNameLalatendu() throws InterruptedException {
		System.out.println("Inside testSingleClickHandling()");
		driver.findElement(By.name("name")).sendKeys("Lalatendu");
		 driver.findElement(By.xpath("//button[text()='Click here to get profession']")).click();
		WebElement webelement = driver.findElement(By.id("profession"));
		String profession = webelement.getAttribute("value");
	    Assert.assertEquals("Java Programmer",profession);
	}
	
	@Test(enabled=true)
	public void testHtmlUnitDriverAsNameSmarita() throws InterruptedException {
		System.out.println("Inside testSingleClickHandlingSmarita()");
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Smarita");
		 driver.findElement(By.xpath("//button[text()='Click here to get profession']")).click();
		WebElement webelement = driver.findElement(By.id("profession"));
		String profession = webelement.getAttribute("value");
	    Assert.assertEquals("Quality Analyst",profession);
	}
	@Test(enabled=true)
	public void testHtmlUnitDriverAsNameNull() throws InterruptedException {
		System.out.println("Inside testSingleClickHandlingNull()");
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("");
		 driver.findElement(By.xpath("//button[text()='Click here to get profession']")).click();
		WebElement webelement = driver.findElement(By.id("profession"));
		String profession = webelement.getAttribute("value");
	    Assert.assertEquals("NA",profession);
	}
	@Test(enabled=true)
	public void testHtmlUnitDriverAsNameUnknown() throws InterruptedException {
		System.out.println("Inside testSingleClickHandlingUnknown()");
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("XYZ");
		//System.out.println("***name= "+driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"));
		driver.findElement(By.xpath("//button[text()='Click here to get profession']")).click();
		WebElement webelement = driver.findElement(By.id("profession"));
		String profession = webelement.getAttribute("value");
		System.out.println("***Profession= "+profession);
	    Assert.assertEquals("NA",profession);
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
