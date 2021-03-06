package com.datadriven.frame.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.datadriven.frame.base.BaseTest;
import com.datadriven.frame.util.DataUtil;
import com.datadriven.frame.util.ExtentManager;
import com.datadriven.frame.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import Page_Property.Global_Functions;


public class DummyTestB extends BaseTest{
	String testCaseName="TestB";
	SoftAssert softAssert; 
	Xls_Reader xls;
	
	
	@Test(dataProvider="getData")
	public void testB(Hashtable<String,String> data) throws IOException {
		softAssert = new SoftAssert();
		test= rep.startTest("DummyTestB");
		test.log(LogStatus.INFO, "Starting the Test B");
			if(!DataUtil.isRunnable(testCaseName, xls) || data.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
				throw new SkipException("Skipping the test as runmode is N");
			}
			test.log(LogStatus.INFO, data.toString());
		//	openBrowser("Chrome");
		//	openBrowser(data.get("Browser"));
			
			
			test.log(LogStatus.INFO, "Open the browser ");
	/*	System.setProperty("webdriver.chrome.driver",  "C:\\chromedriver.exe");
		WebDriver driver=new ChromeDriver(); */
		
//		openWeb("app1url");
		//driver.get("http://www.yahoo.com"); 
	System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();   
	driver.get("http://www.yahoo.com"); 
		
		navigate("app1url");
	//	Global_Functions.dologin();
		test.log(LogStatus.FAIL, "Test B Passed");
	
		
		
		doLogin(envProp.getProperty("username"), envProp.getProperty("password"));
		test.log(LogStatus.PASS, "Test B Passed");
		String check = driver.findElement(By.xpath("//*[@id='TabSFA-main']/a")).getText();
		System.out.println(check+"Bhikhu is fine");
	
	/*	String kp = driver.findElement(By.xpath("//*[@id='i0116']")).getText();
		System.out.println(kp);
		EnterValue("email_xpath", "scribesync@hollandco.com");
		click("button_xpath");  
	
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			EnterValue("password_xpath", "J4icK5CQwUkAHU1R");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			click("button_xpath");  
			
			click("No_User_Pass_Save_xpath"); 
			
		/*	if(!verifyText("Sales_xpath","Sales"))
				reportFailure("Sales is not matching by Dr Patel"); */
		
			
			String check2 = driver.findElement(By.xpath("//*[@id='TabSFA-main']/a")).getText();
			System.out.println(check2);
			
			softAssert.assertTrue(verifyText("Sales_xpath","Sales_Text_xpath"), "Text did not match");
			
			if(!isElementPresent("Sales_xpath"))
				reportFailure("Sales is not present by please verify Xpath");
			
			softAssert.assertTrue(false, "error2CreatedbyKP");
			softAssert.assertTrue(true, "error3CreatedbyKP");
			softAssert.assertTrue(false, "error4CreatedbyKP");
		//	
		//	softAssert.assertTrue(false, "Err 2");
		//	softAssert.assertTrue(true, "Err 3");
		//	softAssert.assertTrue(false, "Err 4");
			
			test.log(LogStatus.PASS, "Test B Passed");
		//	reportFailure("Test Failed");
		
			verifyTitle();
	}
			@Test(priority=2)
			public void Validate_NavToSelectedResource_Name_Displayed(){
				softAssert = new SoftAssert();
				test= rep.startTest("DummyTestB");
				
				test.log(LogStatus.INFO, "Starting the Validate_NavToSelectedResource_Name_Displayed");
			  //  driver.switchTo().frame(0);
			//	Global_Functions.SmartFrames();
				test.log(LogStatus.INFO, "looking element on Grid Table");
				String Name=driver.findElement(By.id("gridBodyTable_primaryField_{C3DDD977-0CC3-E711-A952-000D3A1A9FA9}_3")).getText();
				test.log(LogStatus.INFO, "found element on Grid Table");
				System.out.println(Name);
				wait(3);
				driver.findElement(By.id("gridBodyTable_primaryField_{C3DDD977-0CC3-E711-A952-000D3A1A9FA9}_3")).click();
				wait(1);
				driver.switchTo().defaultContent();

				int total = driver.findElements(By.tagName("iframe")).size();
				System.out.println("Total frames - "+ total);

				String General = driver.findElement(By.linkText(Name)).getText();
				System.out.println(General);
//			assertEquals(General, Name);
				//assertTrue(General.equals(Name));
				
				
			}
			
		
		
	

	@BeforeMethod
	public void init() {
		softAssert = new SoftAssert();
		try {
			super.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@AfterMethod
	public void quit(){
		try {
			softAssert.assertAll();
		}catch (Error e) {
			test.log(LogStatus.FAIL, e.getMessage());
		}
		if(rep!=null){
			rep.endTest(test);
			rep.flush();
		}
		
		if(driver!=null)
			driver.quit();
	}


@DataProvider(parallel=true)
public Object[][] getData() throws IOException{
	init();
	xls = new Xls_Reader(System.getProperty("user.dir")+"\\config\\testcases\\TestData.xlsx");

	
	return DataUtil.getTestData(xls, testCaseName);
} 
}

