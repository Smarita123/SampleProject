package selenium.tutorial.studies;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	
	@DataProvider(name="TestDataFromExcel")
	public static Object[][] createTestDataFromExcel() throws InvalidFormatException, IOException{
		
		System.out.println("Inside createTestDataFromExcel");
		Object[][] data = new Object[][] {};
		File fileObject = new File ("./src/test/resources/CredentialsInExcel.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook (fileObject);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		System.out.println("LastRowNum = "+sheet.getLastRowNum());
		for (int i=1;i<=sheet.getLastRowNum();i++) {
			Row row= sheet.getRow(i);
			String username= row.getCell(0).getStringCellValue();
			String password= row.getCell(1).getStringCellValue();
			System.out.println("Username=" +username+", Password="+password);
			data= ArrayUtils.add(data, new Object[] {username,password});
		}
		workbook.close();
		return data;
		
	}


}
