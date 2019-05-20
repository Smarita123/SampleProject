package com.mvn.Sample1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTest {

	WebDriver driver;
	@Test
	public void methodName() throws IOException {
		// TODO Auto-generated method stub


		System.out.println("Hello World");
		//FileReader reader=new FileReader("testData.properties");  
		InputStream input=SampleTest.class.getClassLoader().getResourceAsStream("testData.properties");
		Properties property=new Properties();
		property.load(input);

		if(property.getProperty("browser").equals("chrome"))
		{
			WebDriverManager.chromedriver().version("73.0.3683.103").setup();
			//System.setProperty(property.getProperty("chromedriver"), property.getProperty("chromeDriverLocation"));
			//System.setProperty("webdriver.chrome.driver", "D:\\Automation\\drivers\\chromedriver.exe");
			driver=new ChromeDriver();  
		}    
		else if(property.getProperty("browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty(property.getProperty("firefoxdriver"), property.getProperty("firefoxDriverLocation"));
			//System.setProperty("webdriver.chrome.driver", "D:\\Automation\\drivers\\chromedriver.exe");
			driver=new FirefoxDriver();  
		}   

		driver.get(property.getProperty("url")); 
		//driver.findElement(By.xpath("//input[@name='email']")).sendKeys("");
		//System.out.println("*****email= "+driver.findElement(By.xpath("//input[@name='email']")).getAttribute("value"));
		
		
		WebElement button= driver.findElement(By.xpath("//button[text()='Double Click to Validate Name']"));
		Actions action=new Actions(driver);
		action.doubleClick(button).perform();
		String str=driver.switchTo().alert().getText();
		
		Assert.assertEquals(str, "You cannot give other name except Lalatendu ");
	

		driver.findElement(By.xpath("//input[@name='psw']")).sendKeys(property.getProperty("psw"));
		System.out.println("*****password= "+driver.findElement(By.xpath("//input[@name='psw']")).getAttribute("value"));

		driver.findElement(By.xpath("//input[@name='psw-repeat']")).sendKeys(property.getProperty("psw-repeat"));
		System.out.println("*****repeat-password= "+driver.findElement(By.xpath("//input[@name='psw-repeat']")).getAttribute("value"));
		driver.findElement(By.xpath("//input[@name='Liquor']")).click();
		driver.findElement(By.xpath("//input[@value='female' ]")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS) ;
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		String str1= "Enter valid email address";


		Assert.assertEquals(driver.switchTo().activeElement().getText(), str1);



	}

}
