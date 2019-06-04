package selenium.tutorial.studies;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSubMenuAfterMouseHover {
	
	WebDriver driver;
	Properties property;
	
	@BeforeTest
	public void setup() throws IOException {
		
		InputStream input = TestSubMenuAfterMouseHover.class.getClassLoader().getResourceAsStream("testData");
		property= new Properties();
		property.load(input);
		
		if("chrome".equalsIgnoreCase(property.getProperty("activeBrowser"))) {
			driver=new ChromeDriver();
		}
		driver.manage().deleteAllCookies();
		driver.get("https://www.spicejet.com");
	}
	@Test
	public void subMenu() {
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.linkText("Add-Ons"))).build().perform();
		
	}
	@AfterTest(enabled=true)
	public void testTearDown() {
	   driver.close();
	}

}
