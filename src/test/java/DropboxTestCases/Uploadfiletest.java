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
import PageElements.UploadFilePage;
import Utilities.ReadOuputFileProperties;

/**
 * @author vinay srivastava
 *
 */
public class Uploadfiletest 
{
	/*
	 * Extent Reports will be loaded before class
	 */
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
	public void UploadFile() throws Exception//This function will verify if file is uplaoded
	{
		logger = extentreport.startTest("Verify New file upload");
		ReadOuputFileProperties readouputfileproperties=new ReadOuputFileProperties();
		Properties prop=readouputfileproperties.OpenOutputFile();
		String Foldername=prop.getProperty("Created_Foler_Name");//fetching file name from output file
		UploadFilePage uploadfilepage=new UploadFilePage();
		boolean uploadfilstatus=uploadfilepage.UploadFile(Foldername, System.getProperty("user.dir")+"\\src\\main\\java\\Utilities\\FileToBeUploaded.txt");
		System.out.println(uploadfilstatus);
		assertTrue(uploadfilstatus,"File Uploaded Succesfully");
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
