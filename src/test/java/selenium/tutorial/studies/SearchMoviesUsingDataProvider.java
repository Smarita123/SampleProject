package selenium.tutorial.studies;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import selenium.tutorial.studies.DataProviderClass;

public class SearchMoviesUsingDataProvider {
	
	WebDriver driver;
	Properties property;
	private ThreadLocal<String> testname= new ThreadLocal<String>();
	
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
		driver.get("http://www.google.com");
	}
	
	@Test(dataProvider="searchYearWiseMovies", dataProviderClass=DataProviderClass.class)
	public void searchMovies(String moviesReleasedInYear) {
		System.out.println(moviesReleasedInYear);
		driver.findElement(By.name("q")).sendKeys(moviesReleasedInYear);
		driver.findElement(By.name("btnK")).submit();;
	}
	
	@AfterMethod
	public void teardown() {
		System.out.println("Inside teardown()");
		driver.close();	
	}

}
