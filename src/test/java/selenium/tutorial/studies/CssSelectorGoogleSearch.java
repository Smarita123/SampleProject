package selenium.tutorial.studies;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class CssSelectorGoogleSearch {
	WebDriver driver;
	@BeforeTest(groups= {"pre-test"})
	public void initialiseDriver() {
		WebDriverManager.chromedriver().version("75.0").setup();
		driver=new ChromeDriver(); 	
		//driver.get("https://www.google.com");
		driver.get("https://learnenglish.britishcouncil.org/speaking");
	}
	@Test (groups= {"run-all-methods"})
	public void google() {
		
		//driver.findElement(By.cssSelector("input[class='gLFyf gsfi']")).sendKeys("smarita");;
		//driver.findElement(By.cssSelector("input[class='gNO89b']")).click();

		driver.findElement(By.cssSelector("a[class='search_icon']")).click();
		driver.findElement(By.cssSelector("input#edit-search-block-form--2")).sendKeys("LearnEnglishKids");;
		//driver.findElement(By.cssSelector("input[id='edit-search-block-form--2']")).sendKeys("LearnEnglishKids");;
		driver.findElement(By.cssSelector("input[id='edit-search-block-form--2']")).sendKeys(Keys.ENTER);;
	}
	

}
