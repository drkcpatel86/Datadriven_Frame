package com.datadriven.frame.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.datadriven.frame.base.BaseTest;
import com.datadriven.frame.util.DataUtil;
import com.datadriven.frame.util.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

public class DummyTestC extends BaseTest{
	String testCaseName="TestC";
	@Test(dataProvider="getData")
	public void TestC(Hashtable<String,String> data) {
		
		test=rep.startTest("Dummy Test C");
		test.log(LogStatus.INFO, "Starting Test C");
//		test.log(LogStatus.FAIL, "Starting Test C");
		
		openBrowser(data.get("Browser"));
		
		test.log(LogStatus.INFO, "Open the browser ");
	}
	
	@AfterMethod
	public void quit(){
		rep.endTest(test);
		rep.flush();
		
		if(driver!=null)
			driver.quit();
	}


	@DataProvider(parallel=true)
	public Object[][] getData() throws IOException{
	super.init();
	Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\config\\testcases\\TestData.xlsx");

	
	return DataUtil.getTestData(xls, testCaseName);
} 
}

