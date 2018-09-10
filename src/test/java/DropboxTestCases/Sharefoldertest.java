/**
 * 
 */
package DropboxTestCases;

import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Drivers.Driver;
import PageElements.ShareFolderPage;
import Utilities.ReadOuputFileProperties;

/**
 * @author vinay
 *
 */
public class Sharefoldertest 
{

	String foldername=null;
	Driver driver=null;
	ExtentReports extentreport;
	ExtentTest logger;
	String email=null;
	@Parameters({"Folder_Name","Shared_Email"})
	@BeforeClass
	public void setup(String Folder_Name,String Shared_Email) throws Exception
	{
		this.driver=new Driver();
		foldername=Folder_Name;
		extentreport=driver.report();
		logger=driver.logger();
		email=Shared_Email;
	}
	@Test
	public void Verifyfoldershared() throws Exception
	{
		logger = extentreport.startTest("Verify share folder");
		ReadOuputFileProperties readouputfileproperties=new ReadOuputFileProperties();
		Properties prop=readouputfileproperties.OpenOutputFile();
		String Foldername=prop.getProperty("Created_Foler_Name");
		ShareFolderPage sharefolderpage=new ShareFolderPage();
		boolean successshared=sharefolderpage.Selectandsharefolder(Foldername, email);
		assertTrue(successshared, "Folder shared successfully");
	}
	@AfterMethod
	public void captureresult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, result.getMethod().getMethodName() + "  is Failed");
		}
	 else  if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, result.getMethod().getMethodName() + "  is Passed");
		}
		extentreport.endTest(logger);
		extentreport.flush();
	}
}
