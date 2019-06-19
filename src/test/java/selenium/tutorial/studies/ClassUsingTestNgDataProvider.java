package selenium.tutorial.studies;

import java.io.InputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ClassUsingTestNgDataProvider {
	
	WebDriver driver;
	
	@BeforeTest
	@Parameters({"activeBrowser","chromeDriverVersion"})
	
	public void setup(String browser, String version) {
		
		if ("chrome".equalsIgnoreCase(browser))
		driver= new ChromeDriver();
		WebDriverManager.chromedriver().version(version).setup();
		driver.get("http://www.google.com");
	}
	
	@Test(dataProvider="searchYearWiseMovies",dataProviderClass=DataProviderClass.class, enabled=false)
	public void displayValues(String year) {
		System.out.println("Inside displayValues()");
		System.out.println("Movie in Year*********"+year);
		
	}
	
	@Test(dataProvider="dataProviderInsideClass")
	public void displayValuesfromDataPrviderInsideClass(String data) {
		System.out.println("Inside displayValuesfromDataPrviderInsideClass()");
		System.out.println("Data*********"+data);
		
	}
	
	@DataProvider(name="dataProviderInsideClass")
	public Object[][] data() {
		return new Object[][] {
			{"Data-1"},{"Data-2"}
		};
	}
	

}
