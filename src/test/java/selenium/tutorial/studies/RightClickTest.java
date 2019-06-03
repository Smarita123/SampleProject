package selenium.tutorial.studies;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class RightClickTest {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() throws IOException {

		InputStream input = RightClickTest.class.getClassLoader().getResourceAsStream("testData.properties");
		Properties property = new Properties();
		property.load(input);
		
		if("chrome".equals(property.getProperty("activeBrowser"))) {
			driver=new ChromeDriver();
		}
		if("firefox".equals(property.getProperty("activeBrowser"))) {
			driver=new FirefoxDriver();	
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(property.getProperty("googleurl"));
	}
	
	@Test
	public void rightClick() throws InterruptedException {
		Actions action = new Actions(driver);
		action.contextClick(driver.findElement(By.linkText("Privacy"))).build().perform();

		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		
		//action.sendKeys("t").build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
		
		driver.manage().wait();
		String title= driver.getTitle();
		Assert.assertEquals("Privacy Policy – Privacy & Terms – Google", title);
	}
	@AfterTest(enabled=true)
	public void testTearDown() {
	   driver.close();
	}

}
