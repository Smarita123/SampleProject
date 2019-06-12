package selenium.tutorial.studies;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGParameterTest {
	
	WebDriver driver;
	
	@BeforeTest
	@Parameters("browser")
	public void setup(String browsername) throws IOException {
		System.out.println("inside setup ::" + browsername);
	}
	
	@Test
	@Parameters("browser")
	public void testWipro(String browsername) {
		System.out.println("Inside testWipro browser name::"+ browsername);
	}
	
	
	public void testWipro1() {
		System.out.println("Inside testWipro1");
	}
	

}
