package selenium.tutorial.studies;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSearchHotel {
	
	WebDriver driver;
	Properties property;
	
	@BeforeTest
	public void setUp() throws IOException {
		InputStream input= TestSearchHotel.class.getClassLoader().getResourceAsStream("testData.properties");
		property= new Properties();
		property.load(input);
		
		if ("chrome".equalsIgnoreCase(property.getProperty("activeBrowser"))) {
			driver= new ChromeDriver();
		}
		
		driver.get("https://www.spicejet.com");
		driver.manage().window().maximize();
	}
	
	@Test
	public void searchHotels() {
		
		driver.findElement(By.linkText("Hotels")).click();
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
		//WebElement destination = driver.findElement(By.xpath("//input[@name='ctl00$mainContent$txtOriginStation1_MST']"));
		WebElement destination = driver.findElement(By.id("ctl00_mainContent_txtOriginStation1_MST"));
		destination.sendKeys("mumbai");
	
		driver.findElement(By.xpath("//input[@type='submit' and @name='ctl00$mainContent$btn_FindFlights']")).submit();
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='txtHotelCity']")).getAttribute("value"),"Mumbai");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@class='admin-text ng-binding']")).getText(),"1 Guest / 1 Room ");

		

	}

}
