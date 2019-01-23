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



public class ContactContactlistUnderSales extends BaseTest {
	String testCaseName="ContactContactlistUnderSales";
	SoftAssert softAssert; 
	Xls_Reader xls;
	
	
	@Test(priority=1,dataProvider="getData")
	public void AccessToContactListUnderSales(Hashtable<String,String> data1) throws IOException {
		softAssert = new SoftAssert();
		test= rep.startTest("AccessToContactListUnderSales");
		test.log(LogStatus.INFO, "Starting the AccessToContactListUnderSales");
			if(!DataUtil.isRunnable(testCaseName, xls) || data1.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
				throw new SkipException("Skipping the test as runmode is N");
			}
			test.log(LogStatus.INFO, data1.toString());

			openBrowser(data1.get("Browser"));
			
			

			navigate("app1url");
			doLogin(envProp.getProperty("username"), envProp.getProperty("password"));
			wait(2);
			
			DefaultLanding();
			wait(2);
			click("Sales_Dropdown_xpath");
			wait(5);
			click("Contact_xpath");
			wait(1);
			DefaultLanding();
			FrameIndex(0);
		//	driver.switchTo().frame(0);
			wait(3);
			
			click("AllContact_xpath");
			wait(2);
	//		driver.findElement(By.xpath("//*[@id='crmGrid_SavedNewQuerySelector']")).click();
			
		//	click("SelectContact_xpath");
			
			driver.findElement(By.xpath("//*[@id='{0D5D377B-5E7C-47B5-BAB1-A5CB8B4AC105}']/a[2]/span/nobr")).click();
			wait(2);
			click("SelectContact1_xpath");
		//	driver.findElement(By.xpath("//*[@id='gridBodyTable']/tbody/tr[2]/td[2]/nobr")).click();
			wait(2);
			doubleClick("SelectContact2_xpath");
			//driver.findElement(By.xpath("//*[@id='gridBodyTable_primaryField_{09193D4E-4FC9-E711-A954-000D3A18032D}_1']/span")).click();
			
			
			test.log(LogStatus.PASS, "Test AccessToContactListUnderSales Passed");
		
	}
	@Test(priority=2,dataProvider="getData",dependsOnMethods={"AccessToContactListUnderSales"})
	public void GeneralInformation_Displayed_ContactListUnderSales(Hashtable<String,String> data1){
		softAssert = new SoftAssert();
		test= rep.startTest("GeneralInformation_Displayed_ContactListUnderSales");
		test.log(LogStatus.INFO, "Starting the GeneralInformation_Displayed_ContactListUnderSales");
		DefaultLanding();
		TotalAvailFrames();
		
		String IName=driver.findElement(By.tagName("Iframe")).getText();
		System.out.println(IName); 
		

		SmartFrames(data1.get("Browser"));  

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(getElement("ContactGeneralInformation_xpath")));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='general information_c']"))));
		
		
		WebElement GeneralInformation = getElement("ContactGeneralInformation_xpath");
		Boolean checGeneralInfoIsDisplayed = GeneralInformation.isDisplayed();
		if (checGeneralInfoIsDisplayed == true) {
		System.out.println("General Information is displayed");
		} else {
			reportFailure("General Informations is not displayed" );
		}
		
		String GeneralInfo = getElement("ContactGeneralInformation_xpath").getText();
		System.out.println(GeneralInfo);
		assertTrue(GeneralInfo.equals("GENERAL INFORMATION"));
		
		IsDisplayed("ContactGeneralInformation_xpath");
		
		test.log(LogStatus.PASS, "Test GeneralInformation_Displayed_ContactListUnderSales Passed");
	}	
	
	@Test(priority=3,dependsOnMethods={"GeneralInformation_Displayed_ContactListUnderSales"})
	public void FirstName_LastName_Displayed_ContactContactListGeneralInformation(){
		softAssert = new SoftAssert();
		test= rep.startTest("FirstName_LastName_Displayed_ContactContactListGeneralInformation");
		test.log(LogStatus.INFO, "Starting the FirstName_LastName_Displayed_ContactContactListGeneralInformation");
		
		String FirstName1 = getElement("FirstName_xpath").getText();
		assertTrue(FirstName1.equals("Josh"));
		
		WebElement LastName=getElement("LastName_xpath");
		Boolean checkLastNameDisplayed = LastName.getText().matches("Bullock");
		System.out.println(LastName);
		
		IsDisplayed("FirstName_xpath");
		IsDisplayed("LastName_xpath");
		
		test.log(LogStatus.PASS, "Test FirstName_LastName_Displayed_ContactContactListGeneralInformation Passed");
		
	}
	
	@Test(priority=4,dependsOnMethods={"FirstName_LastName_Displayed_ContactContactListGeneralInformation"})
	public void ContactContactlistUnderSales(){
		softAssert = new SoftAssert();
		test= rep.startTest("ContactContactlistUnderSales");
		test.log(LogStatus.INFO, "Starting the ContactContactlistUnderSales");
	
		test.log(LogStatus.PASS, "Dependant tests are successful so ContactContactlistUnderSales is successfull");
		
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


