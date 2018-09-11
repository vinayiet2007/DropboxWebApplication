/**
 * 
 */
package DropboxTestCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Drivers.Driver;
import PageElements.Loginpage;

/**
 * @author vinay Srivastava
 * 
 */
public class Logintest extends Driver
{

	Driver driver=null;
	ExtentReports extentreport;
	ExtentTest logger;
	@Parameters({"Browser","Url"})
	@BeforeTest
	public void open_browser(String Browser,String Url) throws Exception
	{
		this.driver=new Driver();
		driver.Openbrowser(Browser,Url);
		driver.loadextentreport();
		extentreport=driver.report();
		logger=driver.logger();
	}
	@Parameters({"User_Email","User_Password"})
	@Test
	public void Verify_Login(String User_Email,String User_Password) throws Exception
	{
		logger = extentreport.startTest("Verify Login Testcase");
		Loginpage loginpage=new Loginpage();
		WebElement Logo_After_Login=loginpage.Login(User_Email, User_Password);
		assertTrue(Logo_After_Login.isDisplayed(), "Login Successful");
	}
	@AfterMethod
	public void captureresult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, result.getThrowable());
			logger.log(LogStatus.FAIL, result.getMethod().getMethodName() + " is Failed");
		}
	 else  if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, result.getMethod().getMethodName() + " is Passed");
		}
		extentreport.endTest(logger);
		extentreport.flush();

	}
}
