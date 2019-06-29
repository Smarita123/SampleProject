package selenium.tutorial.studies;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class GoogleUsingJavaScriptExecutor {
	
	WebDriver driver;
	Properties property;
	
	@BeforeTest
	@Parameters ({"activeBrowser","chromeDriverVersion"})
	public void initialise(String browser, String version) {
		if ("chrome".equalsIgnoreCase(browser)) {
			
			WebDriverManager.chromedriver().version(version).setup();;
			driver=new ChromeDriver();
			driver.get("http://www.google.com");
		}
	}
	
	@Test
	public void googleSearch() throws InstantiationException, IllegalAccessException {
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		jsexecutor.executeScript("document.getElementsByName('q')[0].value='Smarita';");
		jsexecutor.executeScript("document.getElementsByName('btnK')[0].click();");
		String str= jsexecutor.executeScript("return document.getElementsByName('q')[0].value;").toString();
		Assert.assertEquals("Smarita", str);
		
		//jsexecutor.executeScript("arguments[0].value='Smarita';", driver.findElement(By.name("q")));
		//jsexecutor.executeScript("arguments[0].click();", driver.findElement(By.name("btnK")));
		String title=(String) jsexecutor.executeScript("return(document.title);".toString());
		Assert.assertEquals("Smarita - Google Search", title);

	}

}

