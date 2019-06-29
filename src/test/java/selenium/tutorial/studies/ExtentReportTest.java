package selenium.tutorial.studies;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class ExtentReportTest {
	ExtentReports report;
	
	@BeforeTest
	public void report() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./report/sampleReport.html");
		report= new ExtentReports();
		report.attachReporter(reporter);
				
		
	}
	
	@Test
	public void addTest() {
		int a,b,sum;
		a=15; b=25;
		sum=a+b;
		Assert.assertEquals(sum, 40);
		ExtentTest test=report.createTest("addTest()");
		test.log(Status.PASS, "addTest***25+15=40")	;
		
	}
	@AfterTest
	public void flush() {
		report.flush();
	}

}
