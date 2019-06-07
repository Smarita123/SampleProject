package selenium.tutorial.studies;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchFlight {
	
	WebDriver driver;
	Properties property;
	
	@BeforeTest
	public void setup() throws IOException {
		
		InputStream input = SearchFlight.class.getClassLoader().getResourceAsStream("testData.properties");
		property = new Properties();
		property.load(input);
		
		if ("chrome".equalsIgnoreCase(property.getProperty("activeBrowser"))) {
			WebDriverManager.chromedriver().version(property.getProperty("chromeDriverVersion")).setup();
			driver = new ChromeDriver();	
			driver.get("https://www.spicejet.com");
			driver.manage().window().maximize();
		}
	}
	
	@Test
	public void searchFlightsTest() {
		
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).sendKeys(Keys.TAB);
		
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
				
		driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_originStation1']")).sendKeys("Chennai");
		//Select originStation = new Select(driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_originStation1']")));
		//originStation.selectByValue("MAA");
		
		driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_destinationStation1']")).sendKeys("Gwalior");
		//Select destinationStation = new Select(driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_destinationStation1']")));
		//destinationStation.selectByValue("PNQ");
		
		driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).submit();
		
	}
	

}
