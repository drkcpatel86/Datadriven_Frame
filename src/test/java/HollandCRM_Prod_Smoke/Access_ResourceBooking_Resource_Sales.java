package HollandCRM_Prod_Smoke;
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



public class Access_ResourceBooking_Resource_Sales extends BaseTest {
	String testCaseName="Access_ResourceBooking_Resource_Sales";
	SoftAssert softAssert; 
	Xls_Reader xls;
	
	
	@Test(priority=1,dataProvider="getData")
	public void Access_ResourceBooking_Resource_Sales1(Hashtable<String,String> data1) throws IOException {
		softAssert = new SoftAssert();
		test= rep.startTest("Access_ResourceBooking_Resource_Sales");
		test.log(LogStatus.INFO, "Starting the Access_Resource_Resourcelist_Sales");
			if(!DataUtil.isRunnable(testCaseName, xls) || data1.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
				throw new SkipException("Skipping the test as runmode is N");
			}
			test.log(LogStatus.INFO, data1.toString());

			openBrowser(data1.get("Browser"));
			
			

			navigate("app1url");
			doLogin(envProp.getProperty("username"), envProp.getProperty("password"));
			wait(1);
			
			wait(2);
			click("Sales_Dropdown_xpath");
			wait(2);
			click("Resrouce_Sceduling_xpath");
			test.log(LogStatus.PASS, "User is able to navigate to Resource Tab under Sales Successfully");
			}
	@Test(priority=2)
	public void Validate_AccessToListofResourceBooking(){
		softAssert = new SoftAssert();
		test= rep.startTest("ValidateAccessToResourceUnderSales");
		test.log(LogStatus.INFO, "Starting the ValidateAccessToResourceUnderSales");

		
	//	DefaultLanding();

		wait(1);
		DefaultLanding();
		wait(3);
		
		if(!isElementPresent("Resource_Booking_xpath"))
			reportFailure("Resource is not present by please verify Xpath");
		click("Resource_Booking_xpath");
		
		test.log(LogStatus.PASS, "Test Validate_AccessToListofResourceBooking is successfull");
		
		
	//	driver.findElement(By.xpath("//*[@id='{4F49784E-7370-4441-B54E-BF7BE616F312}']/a[2]/span/nobr")).click();
	
	}		
	@Test(priority=3,dataProvider="getData")
	public void Validate_NavToSelected_Booking(Hashtable<String,String> data1){
		softAssert = new SoftAssert();
		test= rep.startTest("Validate_NavToSelected_Booking");
		test.log(LogStatus.INFO, "Starting the Validate_NavToSelected_Booking");
		
	    DefaultLanding();
	    FrameIndex(0);
	    
	    String Name=getElement("ResourceBooking_xpath").getText();
	    
	//	String Name=driver.findElement(By.xpath("//tr[4]/td[2]/nobr/a/li/span/span/span[2]")).getText();
		System.out.println(Name);
		wait(3);
		click("ResourceBooking_xpath");
		wait(1);
		DefaultLanding();

		TotalAvailFrames();
		SmartFrames(data1.get("Browser")); 
	
		String DName=getElement("ResourceBookingName_id").getText();
		//String DName=driver.findElement(By.id("Name_label")).getText();

			String General = driver.findElement(By.linkText(Name)).getText();
		System.out.println(General);  
		assertTrue(DName.equals(Name));
		
		test.log(LogStatus.PASS, "Test Validate_NavToSelected_Booking is successfull");
		
	}
	@Test(priority=4)
	public void Access_ResourceBooking_Resource_Sales(){
		softAssert = new SoftAssert();
		test= rep.startTest("Access_ResourceBooking_Resource_Sales");
		test.log(LogStatus.INFO, "Starting the Access_ResourceBooking_Resource_Sales");
	
		test.log(LogStatus.PASS, "Dependant tests are successful so Access_ResourceBooking_Resource_Sales is successfull");
		
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


