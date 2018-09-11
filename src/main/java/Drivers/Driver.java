package Drivers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
/***
 
 * @author vinay srivastava
 * this class is used to open bowser and create Driver Instance (Firefox, IE and Chrome)
 * This Class will be used as Base Class with other classes
 */

public class Driver 
{
	public static WebDriver driver =null;
	private Properties propertyFile=null;
	public static ExtentReports report;
	private static ExtentTest logger;
	
	
	public void loadextentreport()
	{
		report =new ExtentReports("./Reports/TestExecutionReport.html",true);
		this.report().loadConfig(new File(System.getProperty("user.dir")+"\\src\\main\\java\\Utilities\\extent-config.xml"));
		
	}
	public ExtentTest logger()
	{
		return Driver.logger;
	}
	public ExtentReports report()
	{
		return Driver.report;
	}

	public WebDriver getDriver() 
	{
		return driver;
	}

	/*
 		Openbrowser() Function will open the browser based on browser type definded in config.poperties.
 		This Function will be called in starting of complete suite.
	*/
	public void Openbrowser(String Browser_type, String Url) throws Exception
	{
		if (getDriver()==null)
		{
			//String Browser_type=propertyFile.getProperty("Browser"); 
			if(Browser_type.equalsIgnoreCase("Firefox")) // For Firefox Browser
			{
				System.setProperty("webdriver.gecko.driver",".\\src\\main\\java\\Utilities\\geckodriver16.exe");
		    	driver = new FirefoxDriver();
		    	Thread.sleep(2000);
			}
			if(Browser_type.equalsIgnoreCase("IE"))// For Internet Explorer Browser
			{
				System.setProperty("webdriver.ie.driver", ".\\src\\main\\java\\Utilities\\IEDriverServer64.exe");
		        driver = new InternetExplorerDriver();
		    	Thread.sleep(2000);
			}
			if(Browser_type.equalsIgnoreCase("Chrome")) // For Chrome Browser
			{
				System.setProperty("webdriver.chrome.driver", ".\\src\\main\\java\\Utilities\\chromedriver32.exe");
				driver=new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//Total Implicit wait to load page objects is 20 Secs which hard coded here
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);//Total Implicit wait to load page is 20 Secs which hard coded here
			}
			driver.get(Url);//Driver will open the URL as configured in Config.poperties
			driver.manage().window().maximize();
			
			
			
		}
	}
	
	
}
