package selenium.tutorial.studies;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.gargoylesoftware.css.dom.Property;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DoubleClickHandlingTest {
	
	WebDriver driver;
	
	
	
	@BeforeTest
	public void setup() throws IOException {
		InputStream input= DoubleClickHandlingTest.class.getClassLoader().getResourceAsStream("TestData.properties");
		Properties property = new Properties();
		property.load(input);
		
		if ("chrome".equalsIgnoreCase(property.getProperty("activeBrowser"))) {
			
			WebDriverManager.chromedriver().version(property.getProperty("chromeDriverVersion")).setup();
			driver = new ChromeDriver();
			
		}
		else if ("firefox".equalsIgnoreCase(property.getProperty("activeBrowser"))) {
			
			WebDriverManager.firefoxdriver().version(property.getProperty("firefoxDriverVersion")).setup();
			driver = new ChromeDriver();
						
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(property.getProperty("usermgmtAppURL")); 
	}
	
	@Test(enabled=true)
	public void testWipro() {
		Actions action =new Actions (driver);
		driver.findElement(By.id("organisation")).clear();
		driver.findElement(By.id("organisation")).sendKeys("Wipro");
		WebElement webelement =driver.findElement(By.xpath("//button[text()='Double click me here to find address']"));
		action.doubleClick(webelement).build().perform();
		String address = driver.findElement(By.id("address")).getAttribute("value")	;
		Assert.assertEquals(address, "Bangalore");
	}
	
	@Test(enabled=true)
	public void testPaymentz() {
		Actions action =new Actions (driver);
		driver.findElement(By.id("organisation")).clear();
		driver.findElement(By.id("organisation")).sendKeys("Paymentz");
		WebElement webelement =driver.findElement(By.xpath("//button[text()='Double click me here to find address']"));
		action.doubleClick(webelement).build().perform();
		String address = driver.findElement(By.id("address")).getAttribute("value")	;
		Assert.assertEquals(address, "Mumbai");
	}
	@Test(enabled=true)
	public void testUnknown() {
		Actions action =new Actions (driver);
		driver.findElement(By.id("organisation")).clear();
		driver.findElement(By.id("organisation")).sendKeys("Unknown");
		WebElement webelement =driver.findElement(By.xpath("//button[text()='Double click me here to find address']"));
		action.doubleClick(webelement).build().perform();
		String address = driver.findElement(By.id("address")).getAttribute("value")	;
		Assert.assertEquals(address, "Address not found");
	}
	
	@AfterTest(enabled=true)
	public void testTearDown() {
	   driver.close();
	}
	

}
