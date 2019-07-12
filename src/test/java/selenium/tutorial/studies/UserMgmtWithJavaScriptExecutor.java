package selenium.tutorial.studies;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class UserMgmtWithJavaScriptExecutor {
	
	WebDriver driver;
	@BeforeTest
	@Parameters({"activeBrowser","chromeDriverVersion"})
	public void setup() {
		
	}

}
