package selenium.tutorial.studies;

import java.lang.reflect.Array;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RediffLoginUsingDataProvider {
	
	WebDriver driver;
	Properties property;
	//private ThreadLocal<String> testname= new ThreadLocal<String>();
	
	@BeforeTest
	public void setup() {
		System.out.println("Inside setup");
		WebDriverManager.chromedriver().version("74.0").setup();	
	}
	
	@BeforeMethod
	public void initialize() {
		System.out.println("Inside initialize()");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
	}
	@Test(dataProvider="login", dataProviderClass=DataProviderClass.class)
	public void rediffLogin(String username, String password) {
		System.out.println("login Credentials***"+username +password);
		driver.findElement(By.id("login1")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("proceed")).submit();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[1]")).getText(), "Wrong username and password combination.");
	}
	@AfterMethod
	public void teardown() {
		System.out.println("Inside teardown()");
		driver.close();	
	}

}
