/**
 * 
 */
package DropboxTestCases;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import PageElements.DeleteFolderPage;
import Utilities.ReadOuputFileProperties;

/**
 * @author vinay
 *
 */
public class Deletefoldertest
{
	String foldername=null;
	Driver driver=null;
	ExtentReports extentreport;
	ExtentTest logger;
	@Parameters({"Folder_Name"})
	@BeforeClass
	public void setup(String Folder_Name) throws Exception
	{
		this.driver=new Driver();
		foldername=Folder_Name;
		extentreport=driver.report();
		logger=driver.logger();
	}
	@Test
	public void Verifydeletedfolder() throws Exception
	{
		logger = extentreport.startTest("Verify delete folder");
		ReadOuputFileProperties readouputfileproperties=new ReadOuputFileProperties();
		Properties prop=readouputfileproperties.OpenOutputFile();
		String Foldername=prop.getProperty("Created_Foler_Name");//fetching file name from output file
		DeleteFolderPage deletefolderpage=new DeleteFolderPage();
		boolean successdelete=deletefolderpage.Deletefolder(Foldername);
		assertTrue(successdelete, "Folder Deleted successfully");
	}
	@AfterMethod
	public void captureresult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(LogStatus.PASS, result.getThrowable());
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
