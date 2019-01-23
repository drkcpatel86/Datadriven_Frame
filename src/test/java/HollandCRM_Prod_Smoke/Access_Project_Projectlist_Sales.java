package HollandCRM_Prod_Smoke;
import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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



public class Access_Project_Projectlist_Sales extends BaseTest {
	String testCaseName="Access_Project_Projectlist_Sales";
	SoftAssert softAssert; 
	Xls_Reader xls;
	
	
	@Test(priority=1,dataProvider="getData")
	public void Access_Project_Projectlist_Sales(Hashtable<String,String> data1) throws IOException {
		softAssert = new SoftAssert();
		test= rep.startTest("Access_Project_Projectlist_Sales");
		test.log(LogStatus.INFO, "Starting the Access_Project_Projectlist_Sales");
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

			DefaultLanding();
			wait(3);
			
			click("Sales_Dropdown_xpath");
			test.log(LogStatus.INFO, "User is able to click on Sales");
			wait(4);
			
			click("Projects_UnderSales_xpath");
			wait(1);
			DefaultLanding();	
			FrameIndex(0);
			wait(3);
			click("Nav_All_Open_Closed_And_xpath");
			
			
			click("ProjectSelect_xpath");
			wait(3);
			click("ProjectName_xpath");
			doubleClick("ProjectName_xpath");
			
			DefaultLanding();
			TotalAvailFrames();
		//	driver.switchTo().frame(0);
			SmartFrames(data1.get("Browser")); 
			IsDisplayed("ProjectSummaryTitle_xpath");
			
			test.log(LogStatus.PASS, "Test Access_Project_Projectlist_Sales Passed");
		
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

	@AfterTest
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


