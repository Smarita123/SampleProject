package selenium.tutorial.studies;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
	
	@DataProvider(name="movieslist")
	public static Object[][] moviesTestData() {
		  return new Object[][] {
			  {"Harry Potter"},{"XMen"},{"Ironman"}
		  };
	}
	
	

}
