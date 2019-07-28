package selenium.tutorial.studies;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CssSelectorUserMgmt {
	WebDriver driver;
	@BeforeTest(groups= {"pre-test"})
	public void initialise() {
		WebDriverManager.chromedriver().version("75.0").setup();
		driver=new ChromeDriver();
		driver.get("http://localhost:8081/UserMgmt/UserRegistration.html");
	}
	@Test(groups= {"run-all-methods"})
	public void getVehicleThreeWheeler() {
		//find second and third element of user list
		WebElement secondElement = driver.findElement(By.cssSelector("ul#vehicle > li:nth-child(3)"));
		WebElement thirdElement = driver.findElement(By.cssSelector("ul#vehicle > li:nth-child(3)"));
		System.out.println("Second element = "+secondElement.getText());
		System.out.println("Third element = "+thirdElement.getText());
	}
	@Test(groups= {"run-all-methods"})
	public void register() {
		//Register
		driver.findElement(By.cssSelector("input#name")).clear();
		driver.findElement(By.cssSelector("button[onclick='fillName()']")).click();
		driver.switchTo().alert().sendKeys("Lalatendu");
		driver.switchTo().alert().accept();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#name")).getAttribute("value"), "Lalatendu");;
		driver.findElement(By.cssSelector("button[onclick='getProfession()']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#profession")).getAttribute("value"), "Java Programmer");;
		driver.findElement(By.cssSelector("input#organisation")).sendKeys("Wipro");
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(By.cssSelector("button[ondblclick='getAddress()']")));
		action.build().perform();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#address")).getAttribute("value"), "Bangalore");;
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("smarita.p@paymentz.com"+Keys.TAB);
		String actualMessage=driver.switchTo().alert().getText();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Assert.assertEquals(actualMessage, "Do you want to validate email field");
		driver.switchTo().alert().accept();
		driver.findElement(By.cssSelector("input[name='psw']")).sendKeys("password");
		driver.findElement(By.cssSelector("input[name='psw-repeat']")).sendKeys("password");
		driver.findElement(By.cssSelector("input[name='NonVeg']")).click();
		driver.findElement(By.cssSelector("input[value='male']")).click();
		driver.findElement(By.cssSelector("button[class='registerbtn']")).click();
	}
	@AfterTest (groups= {"after-test"})
	public void tearDown() {
		driver.close();
	}

}
