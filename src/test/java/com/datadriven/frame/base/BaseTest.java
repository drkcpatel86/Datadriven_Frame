package com.datadriven.frame.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.datadriven.frame.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class BaseTest {
	
	public WebDriver driver;
	public Properties prop;
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	public Properties envProp;
	boolean gridRun=false;
	
	
	public void init() throws IOException{
		
		System.out.println("initialse class called");
		if(prop==null){
		prop= new Properties();
		envProp=new Properties();
		try {
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//projectconfig.properties");
		prop.load(fs);
		String env=prop.getProperty("env");
		fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//"+env+".properties");
		envProp.load(fs);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		}}

	
	public void openBrowser1(String bType){
	test.log(LogStatus.INFO, "Opening browser "+bType );
		if(bType.equals("Mozilla"))		
			driver=new FirefoxDriver();
		else if(bType.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+"//chromedriver.exe"));
			driver=new ChromeDriver();
		}
		else if (bType.equals("IE")){
			System.setProperty("webdriver.chrome.driver", prop.getProperty("iedriver_exe"));
			driver= new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	public void openBrowser(String bType){
		test.log(LogStatus.INFO, "Opening browser "+bType );
		if(!gridRun){
			if(bType.equals("Mozilla"))
				driver=new FirefoxDriver();
			else if(bType.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+"//chromedriver.exe"));
				driver=new ChromeDriver();
			}
			else if (bType.equals("IE")){
				System.setProperty("webdriver.chrome.driver", prop.getProperty("iedriver_exe"));
				driver= new InternetExplorerDriver();
			}
		}else{// grid run
			
			DesiredCapabilities cap=null;
			if(bType.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(bType.equals("Chrome")){
				 cap = DesiredCapabilities.chrome();
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser opened successfully "+ bType);

		
	}

	
	public void navigate(String urlKey){
		test.log(LogStatus.INFO, "Navigating to "+prop.getProperty(urlKey) );
		System.out.println(envProp.getProperty(urlKey));	
	driver.get(envProp.getProperty(urlKey));
	}
	
	public void click(String locatorKey) {
		getElement(locatorKey).click();
	}
	
	public void EnterValue(String locatorKey, String data) {
		getElement(locatorKey).sendKeys(data);
	}
	public void type1(String locatorKey,String data){
		getElement(locatorKey).sendKeys(data);
	}
	
	public void type(String locatorKey,String data){
		test.log(LogStatus.INFO, "Tying in "+locatorKey+". Data - "+data);
		getElement(locatorKey).sendKeys(data);
		test.log(LogStatus.INFO, "Typed successfully in "+locatorKey);

	}
	// finding element and returning it
	public WebElement getElement(String locatorKey) {
		WebElement e=null; 
		try {
			if(locatorKey.endsWith("_id"))
				e= driver.findElement(By.id(prop.getProperty(locatorKey)));
			else if(locatorKey.endsWith("_name"))
				e=driver.findElement(By.name(prop.getProperty(locatorKey)));
			else if(locatorKey.endsWith("_xpath"))
				e=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			else {
				Assert.fail("Locator is not correct-" + locatorKey);
			}
		}catch(Exception ex){
			// fail the test and report the error
			reportFailure(ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test - "+ex.getMessage());
		}
		return e;
	}	

	
/***********************Validations***************************/
public boolean verifyTitle(){
	return false;		
}
public boolean isElementPresent(String locatorKey){
	List<WebElement> elementList=null;
	if(locatorKey.endsWith("_id"))
		elementList = driver.findElements(By.id(prop.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_name"))
		elementList = driver.findElements(By.name(prop.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_xpath"))
		elementList = driver.findElements(By.xpath(prop.getProperty(locatorKey)));
	else{
		reportFailure("Locator not correct - " + locatorKey);
		Assert.fail("Locator not correct - " + locatorKey);
	}
	
	if(elementList.size()==0)
		return false;	
	else
		return true;
}

public boolean verifyText(String locatorKey, String expectedTextKey) {
	String actualText=getElement(locatorKey).getText().trim();
	String expectedText=prop.getProperty(expectedTextKey);
	if(actualText.equals(expectedText))
		return true; 
	else 
		return false; 
}



/*****************************Reporting********************************/
public void reportPass(String msg){
	test.log(LogStatus.PASS, msg);
}

public void reportFailure(String msg){
	test.log(LogStatus.FAIL, msg);
	takeScreenShot();
	Assert.fail(msg);
}


public void takeScreenShot(){
	// fileName of the screenshot
	Date d=new Date();
	String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
	// store screenshot in that file
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//put screenshot file in reports
	test.log(LogStatus.INFO,"Screenshot-> "+ test.addScreenCapture(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
	
}
public void wait(int timeToWaitInSec){
	try {
		Thread.sleep(timeToWaitInSec * 1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void waitForPageToLoad() throws InterruptedException {
	wait(1);
	JavascriptExecutor js=(JavascriptExecutor)driver;
	String state = (String)js.executeScript("return document.readyState");
	
	while(!state.equals("complete")){
		wait(2);
		state = (String)js.executeScript("return document.readyState");
	}
}

/************************App functions****/

public boolean doLogin(String username,String password)  {
	test.log(LogStatus.INFO, "Trying to login with "+ username+","+password);
//	click("loginLink_xpath");

	type("email_xpath",username);
	wait(5);
	click("username_next_xpath");
	wait(5);
	type("password_signin_xpath",password);
	wait(3);
	
	click("password_next_xpath");
	click("No_User_Pass_Save_xpath");
	
	
	
	
	
	if(isElementPresent("Sales_Text_xpath")){
		test.log(LogStatus.INFO, "Login Success");
		return true;
	}
	else{
		test.log(LogStatus.INFO, "Login Failed");
		return false;
	}
	
}


}