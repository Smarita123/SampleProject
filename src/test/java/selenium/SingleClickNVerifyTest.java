package selenium;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingleClickNVerifyTest {
	WebDriver driver;
	@Test
	public void loadBrowser() throws IOException {
		
		InputStream input=SingleClickNVerifyTest.class.getClassLoader().getResourceAsStream("testData.properties");
		Properties property=new Properties();
		property.load(input);

		if(property.getProperty("browser").equals("chrome"))
		{
			//WebDriverManager.chromedriver().version("73.0.3683.68").setup();
			//System.setProperty(property.getProperty("HtmlUnitDriver"), property.getProperty("chromeDriverLocation"));
			//System.setProperty(property.getProperty("chromedriver"), property.getProperty("chromeDriverLocation"));
			//System.setProperty("webdriver.chrome.driver", "D:\\Automation\\drivers\\chromedriver.exe");
			driver=new ChromeDriver();  
			//driver=new HtmlUnitDriver();
		}    
		else if(property.getProperty("browser").equals("firefox"))
		{
			//WebDriverManager.firefoxdriver().setup();
			System.setProperty(property.getProperty("firefoxd0river"), property.getProperty("firefoxDriverLocation"));
			//System.setProperty("webdriver.chrome.driver", "D:\\Automation\\drivers\\chromedriver.exe");
			driver=new FirefoxDriver();  
		}   
		//***Load page***
		driver.get(property.getProperty("url")); 
		
		
		//**Fill up the form***
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(property.getProperty("name"));
		driver.findElement(By.xpath("//button[text()='Click here to get profession']")).click();
		
		//Assign values in Name and Profession to Variables
		String name= driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value")	;
		System.out.println("***Name ="+name);
		String profession= driver.findElement(By.xpath("//input[@id='profession']")).getAttribute("value");
		System.out.println("***Profession ="+profession);
		
		SingleClickNVerifyTest obj = new SingleClickNVerifyTest();
		obj.checkProfession(name, profession);	
	}
		
	public void checkProfession(String name, String profession) {
		

		//Profession for Smarita
				if (name.matches("Smarita")) {
					Assert.assertEquals(profession, "Quality Analyst");
					System.out.println("***Tested OK : Profession = Quality Analyst");
				}
				//Profession for Lalatendu
				else if (name.matches("Lalatendu")) {
					Assert.assertEquals(profession, "Java Programmer");
					System.out.println("***Tested OK : Profession = Java Programmer");
				}
				//Profession for Null value in Name
				else if (name.equals("")) {
					Assert.assertEquals(profession, "NA");
					System.out.println("***Tested OK : Profession = NA");
				}
				//Profession for unknown Name
				else {
					Assert.assertEquals(profession, "NA");
					System.out.println("***Tested OK : Profession = Unknown");
				}
	}

}
