package selenium.tutorial.studies;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumDataProviderTest {
	
WebDriver driver;
	
	@BeforeTest
	public void setup() throws IOException {
		System.out.println("inside setup ::");
	}
	
	@Test(dataProvider="searchYearWiseMovies" , dataProviderClass=TestDataCreator.class)
	public void testSearchMooviesByYear(String moviesReleasedInYear) {
		System.out.println("Search movies ->"+ moviesReleasedInYear);
	}
	
}
