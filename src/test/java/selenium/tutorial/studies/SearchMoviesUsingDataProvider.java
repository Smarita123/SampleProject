package selenium.tutorial.studies;
import java.io.InputStream;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
		driver= new ChromeDriver();
		
	}
	
	@Test(dataProvider="searchYearWiseMovies", dataProviderClass=DataProviderClass.class)
	public void searchMovies(String moviesReleasedInYear) {
		System.out.println(moviesReleasedInYear);
	}

}
