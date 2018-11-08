package HollandCRM_Prod_Smoke;
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



public class Access_Project_Projectlist_Sales extends BaseTest {
	String testCaseName="Access_Project_Projectlist_Sales";
	SoftAssert softAssert; 
	Xls_Reader xls;
	
	
	@Test(priority=1,dataProvider="getData")
	public void TestB(Hashtable<String,String> data1) throws IOException {
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
	
		
			wait(2);
			MoveToDefault();
			wait(2);
			
			click("Sales_Dropdown_xpath");
			test.log(LogStatus.INFO, "User is able to click on Sales");
			wait(5);
			
			click("Projects_UnderSales_xpath");
			wait(1);
			MoveToDefault();	
			FrameIndex(0);
			wait(3);
			click("Nav_All_Open_Closed_And_xpath");
			
			
			driver.findElement(By.xpath("//*[@id='{4F49784E-7370-4441-B54E-BF7BE616F312}']/a[2]/span/nobr")).click();
			//	driver.switchTo().frame(2);
				driver.findElement(By.xpath("//*[@id='gridBodyTable']/tbody/tr[7]/td[2]")).click();
				
			//	driver.findElement(By.xpath("//*[@id='gridBodyTable_primaryField_{3AA916E1-7E4D-E811-A960-000D3A1A9EFB}_5']")).click();
				
				driver.switchTo().defaultContent();
			
			
			
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
			reportFailure("Test Failed");
		
			verifyTitle();

		
		
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
	xls = new Xls_Reader(System.getProperty("user.dir")+"\\config\\testcases\\SmokeTestData.xlsx");

	
	return DataUtil.getTestData(xls, testCaseName);
} 
}


