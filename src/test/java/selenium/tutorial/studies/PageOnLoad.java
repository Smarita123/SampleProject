package selenium.tutorial.studies;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class PageOnLoad {
	
	WebDriver driver;
	Properties property;
	
	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		InputStream input = PageOnLoad.class.getClassLoader().getResourceAsStream("testData.properties");
		property = new Properties();
		property.load(input);
		
		if("chrome".equals(property.getProperty("activeBrowser"))) {
			driver= new ChromeDriver();
			
			driver.manage().window().maximize();;
		}
		
		driver.get(property.getProperty("usermgmtAppURL"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
	}
	
	@Test
	public void onLoad() throws InterruptedException {	
		
		String name= driver.findElement(By.id("name")).getAttribute("value");
		String email= driver.findElement(By.name("email")).getAttribute("value");
		Assert.assertEquals(name, "Lalatendu");
		Assert.assertEquals(email, "lalatendu@gmail.com");
	}

}
