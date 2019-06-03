package selenium.tutorial.studies;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import junit.framework.Assert;

public class HandlingAlerts {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() throws IOException {

		InputStream input = HandlingAlerts.class.getClassLoader().getResourceAsStream("testData.properties");
		Properties property = new Properties();
		property.load(input);
		
		if("chrome".equals(property.getProperty("activeBrowser"))) {
			driver=new ChromeDriver();
	
		}
		if("firefox".equals(property.getProperty("activeBrowser"))) {
			driver=new FirefoxDriver();

		}		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(property.getProperty("usermgmtAppURL"));
	}
	
	@Test
	public void testsimpleAlertPopup() {
		driver.findElement(By.linkText("Terms and Privacy")).click();
		//driver.findElement(By.linkText("Sign in")).click();
		String str=driver.switchTo().alert().getText();
		Assert.assertEquals("Listen, I am taking a nap....Will be back at 4:30", str);
		driver.switchTo().alert().accept();
	}
	@Test
	public void testConfirmPopUpAccept() {
		driver.findElement(By.name("email")).sendKeys("new-email");
		driver.findElement(By.name("name")).sendKeys(Keys.TAB);
		String str=driver.switchTo().alert().getText();
		Assert.assertEquals("Do you want to validate Email?", str);
		driver.switchTo().alert().accept();
		
	}
	@Test
	public void testConfirmPopUpDismiss() {
		driver.findElement(By.name("email")).sendKeys("new-email");
		driver.findElement(By.name("name")).sendKeys(Keys.TAB);
		String str=driver.switchTo().alert().getText();
		Assert.assertEquals("Do you want to validate Email?", str);
		driver.switchTo().alert().dismiss();	
	}
	@Test
	public void testpromptPopUpOK() {
		driver.findElement(By.xpath("//button[1]")).click();
		String str=driver.switchTo().alert().getText();
		Assert.assertEquals("Enter Name", str);
		driver.switchTo().alert().sendKeys("Smarita");
		driver.switchTo().alert().accept();	
	}
	@Test
	public void testpromptPopUpCancel() {
		driver.findElement(By.xpath("//button[1]")).click();
		String str=driver.switchTo().alert().getText();
		Assert.assertEquals("Enter Name", str);
		driver.switchTo().alert().sendKeys("Smarita");
		driver.switchTo().alert().dismiss();	
	}
	@AfterTest(enabled=true)
	public void testTearDown() {
	   driver.close();
	}

}
