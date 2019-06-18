package selenium.tutorial.studies;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTestNGParametersMozilla {

	WebDriver driver;

	@BeforeTest
	@Parameters( {"browser", "mozillaDriverVersion"} )
	public void initialiseDriver(String browser, String mozillaDriverVersion){
		
		System.out.println("Active browser***"+browser);
		System.out.println("Driver Version***"+mozillaDriverVersion);
		if ("mozilla".equalsIgnoreCase(browser)) {
			WebDriverManager.firefoxdriver().setup();
			//WebDriverManager.chromedriver().version(chromeDriverVersion).setup();
			driver= new FirefoxDriver();
		}

		driver.get("https://www.spicejet.com");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Hotels")).click();
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
	}

	@AfterMethod
	public void close(){

		driver.quit();

	}


	//@Test (priority=1)
	public void searchHotelsWithCity() {

		//WebElement destination = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_txtOriginStation1_MST' and @name='ctl00$mainContent$txtOriginStation1_MST']"));
		WebElement destination = driver.findElement(By.id("ctl00_mainContent_txtOriginStation1_MST"));
		destination.sendKeys("mumbai");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		//System.out.println("Destination*****"+destination.getAttribute("value"));
		driver.findElements(By.xpath("//li[@class='ui-menu-item']"));
		destination.sendKeys(Keys.DOWN);
		destination.sendKeys(Keys.ENTER);
		driver.findElement(By.id("ctl00_mainContent_ButtonSubmit_MST")).click();
		// driver.findElement(By.xpath("//input[@type='submit' and @name='ctl00$mainContent$btn_FindFlights']")).submit();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));;

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='txtHotelCity']")).getAttribute("value"),"Mumbai");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@class='admin-text ng-binding']")).getText(),"1 Guest / 1 Room");

	}
	//@Test (priority=2)
	public void searchHotelsWithCityandChild() {

		//WebElement destination = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_txtOriginStation1_MST' and @name='ctl00$mainContent$txtOriginStation1_MST']"));
		WebElement destination = driver.findElement(By.id("ctl00_mainContent_txtOriginStation1_MST"));
		destination.clear();
		destination.sendKeys("mumbai");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		//System.out.println("Destination*****"+destination.getAttribute("value"));
		driver.findElements(By.xpath("//li[@class='ui-menu-item']"));
		destination.sendKeys(Keys.DOWN);
		destination.sendKeys(Keys.ENTER);

		Select child = new Select(driver.findElement(By.id("ddl_Child_MST")));
		child.selectByVisibleText("2");

		driver.findElement(By.id("ctl00_mainContent_ButtonSubmit_MST")).click();
		// driver.findElement(By.xpath("//input[@type='submit' and @name='ctl00$mainContent$btn_FindFlights']")).submit();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		// switch to 2nd tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='txtHotelCity']")).getAttribute("value"),"Mumbai");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@class='admin-text ng-binding']")).getText(),"3 Guests / 1 Room");	

	}

	
}
