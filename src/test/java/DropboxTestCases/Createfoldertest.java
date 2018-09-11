package DropboxTestCases;

import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Drivers.Driver;
import PageElements.CreateNewFolderPage;

/**
 * @author vinay srivastava
 *
 */
public class Createfoldertest 
{
	String foldername=null;
	Driver driver=null;
	ExtentReports extentreport;
	ExtentTest logger;
	/*
	 * Extent Reports will be loaded before class
	 */
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
	public void VerifyNewFolder() throws Exception//This function will very if new folder is created
	{
		Date date = new Date();
	    DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
	    String stringDate = sdf.format(date);
		foldername=foldername + stringDate;//Every Folder name will be created with date & time(ms), so that folder should not be duplicated
		logger = extentreport.startTest("Verify New Folder");
		CreateNewFolderPage createnewfolderpage=new CreateNewFolderPage();
		boolean newfoldercreated=createnewfolderpage.Createnewfolder(foldername);
		assertTrue(newfoldercreated, "New Folder Created Successfully");
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
}
