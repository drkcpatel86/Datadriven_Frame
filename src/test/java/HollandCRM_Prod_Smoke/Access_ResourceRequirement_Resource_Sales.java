package HollandCRM_Prod_Smoke;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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



public class Access_ResourceRequirement_Resource_Sales extends BaseTest {
	String testCaseName="Access_ResourceRequirement_Resource_Sales";
	SoftAssert softAssert; 
	Xls_Reader xls;
	
	
	@Test(priority=1,dataProvider="getData")
	public void Validate_AccessToListofResourceRequirment(Hashtable<String,String> data1) throws IOException {
		softAssert = new SoftAssert();
		test= rep.startTest("Access_ResourceRequirement_Resource_Sales ");
		test.log(LogStatus.INFO, "Starting the Access_ResourceRequirement_Resource_Sales");
			if(!DataUtil.isRunnable(testCaseName, xls) || data1.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
				throw new SkipException("Skipping the test as runmode is N");
			}
			test.log(LogStatus.INFO, data1.toString());

			openBrowser(data1.get("Browser"));
			navigate("app1url");
			doLogin(envProp.getProperty("username"), envProp.getProperty("password"));
			wait(3);
			click("Sales_Dropdown_xpath");
			wait(5);
			click("Resrouce_Sceduling_xpath");
			wait(1);
			DefaultLanding();
			wait(3);
			click("Resource_Requirement_xpath");
			test.log(LogStatus.PASS, "User is able to navigate to Requirement Tab under Sales Successfully");
	}
	@Test(priority=2,dataProvider="getData")
	public void Validate_NavToSelected_Requirement(Hashtable<String,String> data1){
		softAssert = new SoftAssert();
		test= rep.startTest("Validate_2NavToSelected_Requirement");
		test.log(LogStatus.INFO, "Starting the Validate_2NavToSelected_Requirement");

		DefaultLanding();
		FrameIndex(0);
		
		String Name=getElement("ResourceReqName_xpath").getText();
		System.out.println(Name);
		wait(3);
		click("ResourceRequirementSelect_xpath");
		wait(1);
		DefaultLanding();
		TotalAvailFrames();
		SmartFrames(data1.get("Browser")); 

		String DName=getElement("DName_id").getText();
		System.out.println(DName);

		assertTrue(DName.equals(Name));
		IsDisplayed("DName_id");
		test.log(LogStatus.PASS, "Test Validate_NavToSelected_Requirement Successfully");
	}	
	
	@Test(priority=3)
	public void Access_ResourceRequirement_Resource_Sales(){
		softAssert = new SoftAssert();
		test= rep.startTest("Access_ResourceBooking_Resource_Sales");
		test.log(LogStatus.INFO, "Starting the Access_ResourceRequirement_Resource_Sales");
	
		test.log(LogStatus.PASS, "Dependant tests are successful so Access_ResourceRequirement_Resource_Sales is successfull");
		
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


