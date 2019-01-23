package com.datadriven.frame.testcases;
import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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



public class Access_Resource_Resourcelist_Sales extends BaseTest {
	String testCaseName="Access_Resource_Resourcelist_Sales";
	SoftAssert softAssert; 
	Xls_Reader xls;
	
	
	@Test(priority=1,dataProvider="getData")
	public void ValidateAccessToResourceUnderSales(Hashtable<String,String> data1) throws IOException {
		softAssert = new SoftAssert();
		test= rep.startTest("Access_Resource_Resourcelist_Sales");
		test.log(LogStatus.INFO, "Starting the Access_Resource_Resourcelist_Sales");
			if(!DataUtil.isRunnable(testCaseName, xls) || data1.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
				throw new SkipException("Skipping the test as runmode is N");
			}
			test.log(LogStatus.INFO, data1.toString());

			openBrowser(data1.get("Browser"));
			
			test.log(LogStatus.INFO, "Opening the browser ");
			navigate("app1url");
			test.log(LogStatus.INFO, "Browser is lauched successfully");
			

	
			doLogin(envProp.getProperty("username"), envProp.getProperty("password"));
			test.log(LogStatus.INFO, "Login Successful");

	//		DefaultLanding();
			wait(1);
			
			click("Sales_Dropdown_xpath");
			test.log(LogStatus.INFO, "User is able to click on Sales");
			wait(3);
			



			click("Resrouce_Sceduling_xpath");
			wait(1);
			driver.switchTo().defaultContent();
			wait(2);
			click("Resource_xpath");
		//	driver.findElement(By.xpath("//*[@id='{4F49784E-7370-4441-B54E-BF7BE616F312}']/a[2]/span/nobr")).click();	
		//,dependsOnMethods={"ValidateAccessToResourceUnderSales"}
	}
	@Test(priority=2)
	public void Validate_NavToSelectedResource_Name_Displayed(){
		
	  //  driver.switchTo().frame(0);
	//	Global_Functions.SmartFrames();
		String Name=driver.findElement(By.id("gridBodyTable_primaryField_{C3DDD977-0CC3-E711-A952-000D3A1A9FA9}_3")).getText();
		System.out.println(Name);
		wait(3);
		driver.findElement(By.id("gridBodyTable_primaryField_{C3DDD977-0CC3-E711-A952-000D3A1A9FA9}_3")).click();
		wait(1);
		driver.switchTo().defaultContent();

		int total = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames - "+ total);

		String General = driver.findElement(By.linkText(Name)).getText();
		System.out.println(General);
//	assertEquals(General, Name);
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
		signOut();
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
	xls = new Xls_Reader(System.getProperty("user.dir")+"\\config\\testcases\\SmokeTestData.xlsx");

	
	return DataUtil.getTestData(xls, testCaseName);
} 
}


