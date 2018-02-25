package TestNGClasses;
// This is on github

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WorkflowSteps {
	//define the class variable
	WebDriver driver;
	String execPath="C:\\Users\\Selenium Drivers\\chromedriver_win32\\chromedriver.exe";

	// set test environments
	@BeforeTest
	public void Setup(){

		System.setProperty("webdriver.chrome.driver",execPath);
		driver=new ChromeDriver();
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TestCase1() throws Exception {

		//Open the website
		driver.get("http://soltech.net/");

		//Click on "Careers" then "Open Positions" Link
		driver.findElement(By.xpath("//*[@id='navbar']/div/ul/li[5]/a")).click();
		//driver.findElement(By.xpath("//*[@id='navbar']/div/ul/li[5]/ul/li[2]/a")).click();
		driver.findElement(By.linkText("Open Positions")).click();

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		driver.switchTo().frame("icims_content_iframe");
		
		Thread.sleep(3000);
		
		//Type "QA" 
		driver.findElement(By.id("jsb_f_keywords_i")).clear();
		driver.findElement(By.id("jsb_f_keywords_i")).sendKeys("Quality");
		
		// Click Search
		driver.findElement(By.id("jsb_form_submit_i")).click();
		
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		
		driver.switchTo().frame("icims_content_iframe");
		
		//Click Automation Test Engineer
		driver.findElement(By.xpath("/html/body/div[2]/div[6]/div[5]/div[3]/a/span[2]")).click();
		
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		driver.switchTo().frame("icims_content_iframe");
		
		//Verify and Print ID 
		String expectedId="2018-2085";
		String actualId=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/div/div[4]/div/dl/dd/span")).getText();

		System.out.println("Actual ID is:  " +"ID " +actualId);
		Assert.assertEquals(expectedId, actualId);
			
	}
		@AfterTest
		public void TearDown(){
			driver.quit();
	}
}


