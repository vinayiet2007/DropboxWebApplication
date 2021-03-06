package PageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Drivers.Driver;

/**
 * @author vinay srivastava
 *
 */

public class UploadFilePage extends Driver
{
	/*
	 * All Page elements declared here
	 */
	WebDriverWait ExplicitWait;
	
	@FindBy(xpath="//a[contains(text(),'Files')]")
	WebElement Link_files;
	
	@FindBy(xpath="/html/body/div[4]/div/div[6]/div[2]/div/div/main/div[1]/div/div/div[1]/table")
	WebElement Table_folderlist;
	
	@FindBy(xpath="/html/body/div[4]/div/div[6]/div[2]/div/div/main/div[2]/div/div[1]/div[2]/div/ul/li[1]/button")
	WebElement Link_uploadnewfile;
	
	@FindBy(xpath="/html/body/div[1]/div/div/div/p")
	WebElement Popup_successmessage;
	
	public UploadFilePage()//Page Elements initialized
	{
		PageFactory.initElements(driver, this);
		ExplicitWait = new WebDriverWait(driver,20);//explicit wait is 20 secs
	}
	
	
	public boolean UploadFile(String Foldername,String Filepath,String Browser) throws Exception//This function will upload the file
	{
		String Successmessage=null;
		Thread.sleep(3000);
		
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Link_files)).click();
		Thread.sleep(2000);
		List<WebElement> Tablerows=driver.findElements(By.xpath("/html/body/div[4]/div/div[6]/div[2]/div/div/main/div[1]/div/div/div[1]/table/tbody/tr/td[1]"));
		
		String Xpathbeforetablerow="/html/body/div[4]/div/div[6]/div[2]/div/div/main/div[1]/div/div/div[1]/table/tbody/tr[";
		String Xpathaftertablerow="]//div[@class='brws-file-name-element']/span";
		for (int i=1;i<=Tablerows.size();i++)//Verify each row in the folder table
		{
			if(driver.findElement(By.xpath(Xpathbeforetablerow + (i) + Xpathaftertablerow )).getText().contains(Foldername))
			{
				Actions action = new Actions(driver); 
				 action.moveToElement(driver.findElement(By.xpath(Xpathbeforetablerow + (i) + Xpathaftertablerow ))).click().build().perform();
				 Thread.sleep(3000);
				 break;
			}
		}
		ExplicitWait.until(ExpectedConditions.visibilityOf(Link_uploadnewfile)).click();
		Thread.sleep(4000);//After submitting file driver will wait for 4 Secs
		/*
		 * Fileupload.exe is external utility file.
		 * Created from AUtoIT(utility will be different for different browsers)
		 */
		if(Browser.equalsIgnoreCase("Chrome"))
		{
		Runtime.getRuntime().exec("cmd.exe /c Start "+System.getProperty("user.dir")+"\\src\\main\\java\\Utilities\\Fileupload.exe "  + Filepath );
		Successmessage=ExplicitWait.until(ExpectedConditions.visibilityOf(Popup_successmessage)).getText();
		}
		else if(Browser.equalsIgnoreCase("Firefox"))
		{
			Runtime.getRuntime().exec("cmd.exe /c Start "+System.getProperty("user.dir")+"\\src\\main\\java\\Utilities\\FileUploadFF.exe  "  + Filepath );
			Successmessage=ExplicitWait.until(ExpectedConditions.visibilityOf(Popup_successmessage)).getText();
		}
		System.out.println(Successmessage);
		
		try
		{
				while(Successmessage.contains("Uploading"))
				{
					Successmessage=ExplicitWait.until(ExpectedConditions.elementToBeClickable(Popup_successmessage)).getText();
					if(Successmessage.contains("Uploaded FileToBeUploaded.txt"))//file name is fixed
					{
						return true;
					}
			}
		}
		catch(StaleElementReferenceException ex)
		{
			Successmessage=ExplicitWait.until(ExpectedConditions.elementToBeClickable(Popup_successmessage)).getText();
			if (Successmessage.contains("Uploaded FileToBeUploaded.txt"))
			{
				return true;
			}
		}
		
		return false;
		
	}
}
