package selenium.tutorial.studies;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGSingleParamProvider {
	
WebDriver driver;
	
	@BeforeTest
	public void setup() throws IOException {
		System.out.println("inside setup ::");
	}
	
	@Test(dataProvider="movieslist" , dataProviderClass=TestDataProvider.class)
	public void testWipro(String moviename) {
		System.out.println("moie name printed as::"+ moviename);
	}
	
}
