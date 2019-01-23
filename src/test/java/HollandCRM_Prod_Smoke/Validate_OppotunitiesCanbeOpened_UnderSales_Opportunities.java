package HollandCRM_Prod_Smoke;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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



public class Validate_OppotunitiesCanbeOpened_UnderSales_Opportunities extends BaseTest {
	String testCaseName="Validate_OppotunitiesCanbeOpened_UnderSales_Opportunities";
	SoftAssert softAssert; 
	Xls_Reader xls;
	
	
	@Test(priority=1,dataProvider="getData")
	public void Validate_AccessToListofOpportunities(Hashtable<String,String> data) throws IOException {
		softAssert = new SoftAssert();
		test= rep.startTest("Validate_OppotunitiesCanbeOpened_UnderSales_Opportunities");
		test.log(LogStatus.INFO, "Starting the Validate_OppotunitiesCanbeOpened_UnderSales_Opportunities");
			if(!DataUtil.isRunnable(testCaseName, xls) || data.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
				throw new SkipException("Skipping the test as runmode is N");
			}
			test.log(LogStatus.INFO, data.toString());

			openBrowser(data.get("Browser"));
			
			

			navigate("app1url");
			doLogin(envProp.getProperty("username"), envProp.getProperty("password"));
			wait(2);
			test.log(LogStatus.PASS, "Test Validate_AccessToListofOpportunities Passed");
	}	
			
	@Test(priority=2,dataProvider="getData")
	public void Validate_OpportunitySelected_Name_Displayed(Hashtable<String,String> data)throws IOException{
		softAssert = new SoftAssert();
		test= rep.startTest("GeneralInformation_Displayed_ContactListUnderSales");
		test.log(LogStatus.INFO, "Starting the GeneralInformation_Displayed_ContactListUnderSales");
		
		if(!DataUtil.isRunnable(testCaseName, xls) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}	
			
			
			driver.switchTo().defaultContent();
			click("Sales_Dropdown_xpath");
			wait(5);
			click("Opportunities_UnderSales_xpath");
			wait(1);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			wait(3);
			click("Nav_All_Open_Closed_And_xpath");
			driver.findElement(By.xpath("//*[@id='{7F7FB7D5-4863-E711-80FB-5065F38B8481}']/a[2]/span/nobr")).click();
		//	driver.switchTo().frame(2);
			driver.findElement(By.xpath("//*[@id='gridBodyTable']/tbody/tr[6]/td[2]")).click();
			
		//	driver.findElement(By.xpath("//*[@id='gridBodyTable_primaryField_{3AA916E1-7E4D-E811-A960-000D3A1A9EFB}_5']")).click();
			
			driver.switchTo().parentFrame();
			int total = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames - "+ total);	
			driver.switchTo().frame(1);
			String Oppotunity = driver.findElement(By.xpath("//*[@id='header_crmFormSelector']/nobr/span")).getText();
			System.out.println(Oppotunity);
	//		assertTrue(Oppotunity.equals("OPPORTUNITY"));
			test.log(LogStatus.PASS, "Test Validate_OpportunitySelected_Name_Displayed Passed");
			
			
	}
	

	
	@Test(priority=3) 
	public void Validate_AccountName_Displayed_AccountListUnderSales4() {
		
		WebElement AccountName1=driver.findElement(By.xpath("//*[@id='name']/div[1]"));
		String AccountName = driver.findElement(By.xpath("//*[@id='name']/div[1]")).getText();
		
	//	assertTrue(AccountName.equals("Adkins Energy"));
		Boolean checkAccountInfoIsDisplayed2 = AccountName1.getText().matches("Adkins Energy");
		System.out.println(AccountName);
		
		test.log(LogStatus.PASS, "Test Validate_AccountName_Displayed_AccountListUnderSales4 Passed");
	}

	@BeforeMethod
	public void init() {
		softAssert = new SoftAssert();
		
		
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
	super.init();
	xls = new Xls_Reader(System.getProperty("user.dir")+"\\config\\testcases\\SmokeTestData.xlsx");
	Object[][] data= DataUtil.getTestData(xls, "Validate_OppotunitiesCanbeOpened_UnderSales_Opportunities");
	
	return data;
} 
}


