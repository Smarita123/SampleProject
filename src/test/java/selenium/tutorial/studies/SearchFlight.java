package selenium.tutorial.studies;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
			driver.manage().window().maximize();
		}
		driver.get("https://www.spicejet.com");
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void searchFlightsTest() {
		System.out.println("Inside Search Flight test");
		
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('ctl00_mainContent_ddl_originStation1_CTXT').value='Amritsar (ATQ)';");
				
		js.executeScript("document.getElementById('ctl00_mainContent_ddl_destinationStation1_CTXT').value='Bengaluru (BLR)';");
		
		//WebElement origin=driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']"));
		//setStation(driver, origin, "Amritsar (ATQ)");
		
		//driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_destinationStation1']")).sendKeys("Gwalior");
		//Select destinationStation = new Select(driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_destinationStation1']")));
		//destinationStation.selectByValue("PNQ");
		//WebElement destination = driver.findElement(By.xpath("//input[@id='ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1_CTXT"));
		//setStation(driver, destination, "Gwalior (GWL)");
		//setStation(driver, destination, "Bengaluru (BLR)");
		
		//driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).submit();
		
	}
	
	public void setStation(WebDriver driver, WebElement element, String station) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		//js.executeScript("arguments[0].setAttribute('value','"+station+"');",element);
		js.executeScript("arguments[0].setAttribute('value','"+station+"');",element);
				
		
	}
	

}