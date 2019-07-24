package Testing;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.LogStatus;

public class SampleStandAlone {
	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+"//chromedriver.exe"));
		driver=new ChromeDriver();

		
		driver.get("https://www.amazon.com/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Sports");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='nav-link-accountList']")).click();
		
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("sultansports14@gmail.com");
		driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Test1234");
	
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		
		//Taking a screenshot 
		
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		 // now copy the  screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, new File("C:/selenium/error.png"));
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// getting address 
		
		String address = driver.findElement(By.xpath("//a[@class='nav-a nav-a-2 a-popover-trigger a-declarative']")).getText();
		System.out.println(address);
		
		driver.quit();
	
	
	}
	
}
