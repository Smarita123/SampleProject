package selenium.tutorial.studies;

import org.testng.annotations.DataProvider;

public class TestDataCreator {
	
	@DataProvider(name="searchYearWiseMovies")
	public static Object[][] moviesTestData() {
		  return new Object[][] {
			  {"2018 movies list"},{"2017 movies list"},{"2016 movies list"}
		  };
	}
	
	

}
