package selenium.tutorial.studies;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name="searchYearWiseMovies")
	public static Object[][] moviesTestData(){
		return new Object[][] {
			{"2018 movies list"}, {"2017 movies list"}, {"2016 movies list"}
		};
	}
	
	@DataProvider(name="login")
	public static Object[][] credentialsTestData(){
		return new Object[][] {
			{"User", "Passwrd"}, 
			{"Username", "Password"}, 
			{"Username1", "Password1"}
		};
	}


}
