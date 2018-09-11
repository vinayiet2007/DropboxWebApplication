/**
 * 
 */
package DropboxTestCases;

import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Drivers.Driver;
import PageElements.LogoutPage;
import PageElements.ShareFolderPage;
import Utilities.ReadOuputFileProperties;

/**
 * @author vinay srivastava
 *
 */
public class Logouttest 
{
	Driver driver=null;
	ExtentReports extentreport;
	ExtentTest logger;
	@BeforeTest
	public void setup() throws Exception
	{
		this.driver=new Driver();
		extentreport=driver.report();
		logger=driver.logger();
	}
	@Test
	public void Verifylogout() throws Exception
	{
		logger = extentreport.startTest("Verify logout");
		LogoutPage logout=new LogoutPage();
		boolean logoutsuccess=logout.Logout();
		assertTrue(logoutsuccess, "User Logged out successfully");
	}
	@AfterMethod
	public void captureresult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, result.getThrowable());
			logger.log(LogStatus.FAIL, result.getMethod().getMethodName() + "  is Failed");
		}
	 else  if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, result.getMethod().getMethodName() + "  is Passed");
		}
		extentreport.endTest(logger);
		extentreport.flush();
	}
	@AfterSuite
	public void displayresult()
	{
		driver.getDriver().get(System.getProperty("user.dir")+"\\Reports\\TestExecutionReport.html");
	}
	
}
