package VivekSir_framwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(selenium.tutorial.studies.reports.ExtentReporterNG.class)
public class HybridUsingCssSelector {
	 final static Logger logger = LoggerFactory.getLogger(HybridUsingCssSelector.class);

	// Global Variables;
	String xlPath, xlRes_TS, xlRes_TC, xlRes_TD;
	int xRows_TC, xRows_TS, xCols_TC, xCols_TS, xRows_TD, xCols_TD;
	String[][] xlTC, xlTS, xlTD;// 2D Array of Test Data, Test case, Test steps
	String vKW, vIP1, vIP2, selector;
	WebDriver driver;
	String vTS_Res, vTC_Res, vTD_Res;
	double starttime, endtime;
	
	@BeforeTest(groups= {"run-all-methods"})
	public void myBefore() throws Exception{
		// driver = new FirefoxDriver();
	    // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    
		starttime= System.currentTimeMillis();
		
		WebDriverManager.chromedriver().version("75.0").setup();
		driver=new ChromeDriver();
		//smarita's folder path
		xlPath = ".\\src\\test\\resources\\HybridWithCSS.xls";
	    xlRes_TS= ".\\src\\test\\resources\\Results\\HF1_TS_Res";
	    xlRes_TC= ".\\src\\test\\resources\\Results\\HF1_TC_Res";
	    
		
		//xlPath = "C:\\Users\\user\\gitproject\\SeleniumPractice\\src\\test\\resources\\Test_HF_Selenium.xls";
	    //xlRes_TS= "C:\\Users\\user\\gitproject\\SeleniumPractice\\src\\test\\resources\\Results\\HF1_TS_Res";
	    //xlRes_TC= "C:\\Users\\user\\gitproject\\SeleniumPractice\\src\\test\\resources\\Results\\HF1_TC_Res";
	    
	   // xlRes_TD= "C:\\SLT_Oct_2015\\HF1_TD_Res.xls";
		xlTC = readXL(xlPath, "Test Cases");
		xlTS = readXL(xlPath, "Test Steps");
		xlTD = readXL(xlPath, "Test Data");
		
		xRows_TC = xlTC.length;
		xCols_TC = xlTC[0].length;
		logger.info("TC Rows are " + xRows_TC);
		logger.info("TC Cols are " + xCols_TC);
		
		xRows_TS = xlTS.length;
		xCols_TS = xlTS[0].length;
		logger.info("TS Rows are " + xRows_TS);
		logger.info("TS Cols are " + xCols_TS);
		
		xRows_TD = xlTD.length;
		xCols_TD = xlTD[0].length;
		logger.info("TD Rows are " + xRows_TD);
		logger.info("TD Cols are " + xCols_TD);
	}    
	
	
	@Test(groups= {"run-all-methods"})
	public void mainTest() throws Exception{
		
		logger.info("xlTC rows -> {} and xlTS rows -> {} and xlTD rows ->{}",xRows_TC, xRows_TS ,xRows_TD );
		
		//PropertiesConfigurator is used to configure logger from properties file
		//PropertyConfigurator.configure("D:\\Eclipse_Workspace\\SeleniumPractice\\src\\log4j.properties");
		
		for (int k=1; k<xRows_TD; k++){  // test data
			if (xlTD[k][1].equals("Y")) {
				logger.info("TD ready for execution : " + xlTD[k][0]);
				for (int i=1; i<xRows_TC ; i++){  // test case sheet
					if (xlTC[i][2].equals("Y")){
						//xlTS = readXL(xlPath, "Test Steps");
						logger.info("TC ready for execution : " + xlTC[i][0]);
						vTC_Res = "Pass"; // Assume to begin that TC is a pass
						int stepNum = 0;
						for (int j=1; j<xRows_TS; j++){	// test steps sheet
							if (xlTC[i][0].equals(xlTS[j][0])){	
								stepNum++;
								vKW = xlTS[j][3];// keyword  Enteremail
								selector=xlTS[j][4]; //Selector
								vIP1 = xlTS[j][5];  // Ip1/ xpath of feild  //*[@id='Email']
								vIP2 = xlTS[j][6];// IP2 data for that feild   vEmailid
								logger.info("~~~~~~TD to pick data from : " + xlTD[k][0]);
								vIP1 = getTestDataValue(vIP1, k);
								vIP2 = getTestDataValue(vIP2, k);
								logger.info(">>>vIP1>>>"+ vIP1);
								logger.info(">>>vIP2>>>"+ vIP2);
								vTS_Res = "Pass"; // Assume to begin that TS is a pass
								logger.info("KW: " + vKW);
								logger.info("IP1: " + vIP1);
								logger.info("IP2: " + vIP2);
								try {
									executeKW(vKW, selector, vIP1, vIP2);
									if (vTS_Res.equals("Pass")){
										vTS_Res = "Pass";
									} else {
										vTS_Res = "Verification Failed";
										vTC_Res = "Fail";
										xlTS[j][8] = "Look at the screenshot.";
										takeScreenshot("D:\\Eclipse_Workspace\\SeleniumPractice\\src\\test\\resources\\Screenshots"+xlTD[k][0]+"_"+xlTC[i][0]+"_"+stepNum+".jpg");
									}
								} catch (Exception myError){
									logger.error("Error : " + myError);
									vTS_Res = "Fail";
									vTC_Res = "Fail";
									xlTS[j][8] = "Error : " + myError;
									takeScreenshot("D:\\Eclipse_Workspace\\SeleniumPractice\\src\\test\\resources\\Screenshots"+xlTD[k][0]+"_"+xlTC[i][0]+"_"+stepNum+".jpg");
								}
								// Update the actual test data value before writing results
								xlTS[j][5] = vIP1;
								xlTS[j][6] = vIP2;
								xlTS[j][7] = vTS_Res;
								//writeXL(xlRes_TS+xlTD[k][0]+".xls", "TestSteps", xlTS);
								//writeXL(xlRes_TC+xlTD[k][0]+".xls", "TestCases", xlTC);							
							}
						}	
						xlTC[i][3] = vTC_Res;
					} else {
						logger.info("TC NOT ready for execution : " + xlTC[i][0]);
					}
				}
				// Update the results of the KDF for each set of Test Data
				writeXL(xlRes_TS+xlTD[k][0]+".xls", "TestSteps", xlTS);
				writeXL(xlRes_TC+xlTD[k][0]+".xls", "TestCases", xlTC);
				xlTS = readXL(xlPath, "Test Steps");// readXL will reset the xlTS variables (IP1 and IP2 values of this iteration), so it reads fresh values from Excel
			} else {
				logger.info("TD row not ready for execution : " + xlTD[k][0]);
			}
		}
		
				
	}
	
	@AfterTest(groups= {"after-test"})
	public void myAfterTest() throws Exception{
		driver.close();
		endtime=System.currentTimeMillis();
		Double time = (endtime-starttime)/1000;
		logger.info("Total Time taken {} in sec" ,time);
		
	}
	
	public String getTestDataValue(String fIP, int fK){
		logger.info("fK value is " + fK);	//test data row number
		switch (fIP){
			case "vURL":
				return xlTD[fK][2];
			case "vLoanAmt":
				return xlTD[fK][3];
			case "vTermYears":
				return xlTD[fK][4];
			case "vTermMonths":
				return xlTD[fK][5];
			case "vRate":
				return xlTD[fK][6];
			case "vPayAmt":
				return xlTD[fK][7];
			case "vSearchTerm":
				return xlTD[fK][8];
			default :
				return fIP;
		}
	}
	public void takeScreenshot(String fPath) throws Exception{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File(fPath));
	}
	public void executeKW(String fKW, String selector, String fIP1, String fIP2){
		// Purpose: Executes the corr. function
		// I/P: KW, IP1, IP2
		// O/P:
		
		switch (fKW){
			case "goToUrl":
					goToUrl(fIP1);
				break;
			case "clearText":
					clearText(selector, fIP1);
					break;
			case "typeText":
					typeText(selector, fIP1, fIP2);
					break;
			case "clickElement":
					clickElement(selector, fIP1);
					break;
			case "closeBrowser":
					closeBrowser();
					break;
			case "verifyText":
					vTS_Res = verifyText(selector, fIP1, fIP2);
					break;
			case "verifyValue":
					vTS_Res = verifyValue(selector, fIP1, fIP2);
					break;
			case "launchDriver":
					launchDriver();
					break;
			default :
				logger.error("Keyword is missing.");
		
		}
	}
	
	// Reusable web based actions performed by users
	
		public void launchDriver(){
			// Purpose: Launches a driver
			// I/P: -
			// O/P: -
			WebDriverManager.chromedriver().version("74.0").setup();
			driver = new ChromeDriver();
			logger.info("driver initialised");
			
			//driver = new FirefoxDriver();
		    //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		public void clearText(String selector, String path){
			// Purpose: Clears any text present in a editable text field
			// I/P: xPath of the element that you want to clear
			// O/P:
			if ("xpath".equals(selector)){
				driver.findElement(By.xpath(path)).clear();
			}
			else if ("CssSelector".equalsIgnoreCase(selector)){
				driver.findElement(By.cssSelector(path)).clear();
			}
		}
		
		public void typeText(String selector, String path, String fText){
			// Purpose: Types text into an editable text field
			// I/P: xPath of the element, and the text you need to enter
			// O/P:
			if ("xpath".equals(selector)){
			driver.findElement(By.xpath(path)).sendKeys(fText);
			}
			else if ("CssSelector".equalsIgnoreCase(selector)){
				driver.findElement(By.cssSelector(path)).sendKeys(fText);
			}
		}
		
		public void clickElement(String selector, String path){
			// Purpose: Clicks on any element on webpage
			// I/P: xPath of the element
			// O/P:
			if ("xpath".equals(selector)){
				driver.findElement(By.xpath(path)).click();
			}
			else if ("CssSelector".equalsIgnoreCase(selector)){
					driver.findElement(By.cssSelector(path)).click();
			}
		}
		
		public void goToUrl(String fUrl){
			// Purpose: Takes the browser to a URL
			// I/P: URL
			// O/P:
			
			driver.get(fUrl);
		}
		
		public void waitFor(int fMiliSeconds) throws Exception{
			// Purpose: Make the program wait for certain time
			// I/P: Milli seconds to wait
			// O/P:
			Thread.sleep(fMiliSeconds);
			
		}
		@AfterTest
		public void closeBrowser(){
			// Purpose: Close the browser
			// I/P: 
			// O/P:
			
			driver.quit();
		}
		
		public String verifyText(String selector, String path, String fText){
			// Purpose: Verifies a text in a specific element
			// I/P: xPath, Text to verify
			// O/P: pass or fail
			
			String fAppText="";
			
			if ("xpath".equals(selector)){
				fAppText = driver.findElement(By.xpath(path)).getText();
			}
			else if ("CssSelector".equalsIgnoreCase(selector)){
					driver.findElement(By.cssSelector(path)).getText();
			}
			if (fAppText.equals(fText)){
				return "Pass";
			} else {
				return "Fail";
			}
		}
		
		public String verifyValue(String selector, String path, String fText){
			// Purpose: Verifies a value in a specific element
			// I/P: xPath, Text to verify
			// O/P: pass or fail
			
			String fAppText=""; //initialize
			
			if ("xpath".equals(selector)){
				fAppText = driver.findElement(By.xpath(path)).getAttribute("value");
			}
			else if ("CssSelector".equalsIgnoreCase(selector)){
					driver.findElement(By.cssSelector(path)).getAttribute("value");
			}
			if (fAppText.equals(fText)){
				return "Pass";
			} else {
				return "Fail";
			}
		}
	
	// Teach Java to R/W from MS Excel
	// Method to read XL
	public String[][] readXL(String fPath, String fSheet) throws Exception{
		// Inputs : XL Path and XL Sheet name
		// Output : 
		
			String[][] xData;  
			int xRows, xCols;

			File myxl = new File(fPath);                                
			FileInputStream myStream = new FileInputStream(myxl);                                
			HSSFWorkbook myWB = new HSSFWorkbook(myStream);                                
			HSSFSheet mySheet = myWB.getSheet(fSheet);                                 
			xRows = mySheet.getLastRowNum()+1;                                
			xCols = mySheet.getRow(0).getLastCellNum();   
			//System.out.println("Total Rows in Excel are " + xRows);
			//System.out.println("Total Cols in Excel are " + xCols);
			xData = new String[xRows][xCols];        
			for (int i = 0; i < xRows; i++) {                           
					HSSFRow row = mySheet.getRow(i);
					for (int j = 0; j < xCols; j++) {                               
						HSSFCell cell = row.getCell(j);
						String value = "-";
						if (cell!=null){
							value = cellToString(cell);
						}
						xData[i][j] = value;      
						//System.out.print(value);
						//System.out.print("----");
						}        
					//System.out.println("");
					}    
			myxl = null; // Memory gets released
			return xData;
	}
	
	//Change cell type
	public static String cellToString(HSSFCell cell) { 
		// This function will convert an object of type excel cell to a string value
		CellType type = cell.getCellType();                        
		Object result;                        
		switch (type) {                            
			case NUMERIC: //0                                
				result = cell.getNumericCellValue();                                
				break;                            
			case STRING: //1                                
				result = cell.getStringCellValue();                                
				break;                            
			case FORMULA: //2                                
				throw new RuntimeException("We can't evaluate formulas in Java");  
			case BLANK: //3                                
				result = "%";                                
				break;                            
			case BOOLEAN: //4     
				result = cell.getBooleanCellValue();       
				break;                            
			case ERROR: //5       
				throw new RuntimeException ("This cell has an error");    
			default:                  
				throw new RuntimeException("We don't support this cell type: " + type); 
				}                        
		return result.toString();      
		}
	
	// Method to write into an XL
	public void writeXL(String fPath, String fSheet, String[][] xData) throws Exception{

	    	File outFile = new File(fPath);
	        HSSFWorkbook wb = new HSSFWorkbook();
	        HSSFSheet osheet = wb.createSheet(fSheet);
	        int xR_TS = xData.length;
	        int xC_TS = xData[0].length;
	    	for (int myrow = 0; myrow < xR_TS; myrow++) {
		        HSSFRow row = osheet.createRow(myrow);
		        for (int mycol = 0; mycol < xC_TS; mycol++) {
		        	HSSFCell cell = row.createCell(mycol);
		        	cell.setCellType(CellType.STRING);
		        	cell.setCellValue(xData[myrow][mycol]);
		        }
		        FileOutputStream fOut = new FileOutputStream(outFile);
		        wb.write(fOut);
		        fOut.flush();
		        fOut.close();
	    	}
		}
}

