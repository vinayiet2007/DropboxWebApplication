/**
 * 
 */
package PageElements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Drivers.Driver;
import Utilities.ReadOuputFileProperties;

/**
 * @author vinay srivastava
 *
 */
public class CreateNewFolderPage extends Driver
{
WebDriverWait ExplicitWait;
	
	/*
	 	Login Page Elements declared
	*/

	@FindBy(xpath="//a[contains(text(),'Files')]")
	WebElement Link_files;
	
	@FindBy(xpath="/html/body/div[4]/div/div[6]/div[2]/div/div/main/div[2]/div/div[1]/div[2]/div/ul/li[2]/button")
	WebElement Link_newfolder;
	
	@FindBy(className="c-input")
	WebElement Textbox_newfoldername;
	
	@FindBy(xpath="/html/body/div[4]/div/div[6]/div[2]/div/div/main/div[1]/div/div/div[1]/table")
	WebElement Table_folderlist;
	
	@FindBy(id="notify-msg")
	WebElement Span_newfolderconfirmation;
	
	
	public CreateNewFolderPage()
	{
		PageFactory.initElements(driver, this);
		ExplicitWait = new WebDriverWait(driver,20);
	}
	
	public boolean Createnewfolder(String Foldername) throws Exception//this function will create new folder
	{
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Link_files)).click();
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Link_newfolder)).click();
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Textbox_newfoldername)).sendKeys(Foldername);
		Thread.sleep(1000);
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Textbox_newfoldername)).sendKeys(Keys.ENTER);
		System.out.println(ExplicitWait.until(ExpectedConditions.elementToBeClickable(Span_newfolderconfirmation)).getText());
		if(ExplicitWait.until(ExpectedConditions.elementToBeClickable(Span_newfolderconfirmation)).getText().contains("Created folder " + Foldername))
		{
			/*
			 * Folder will be created and folder name will be saved in output file which will be used as input in upload file test case
			 */
			ReadOuputFileProperties outputfile=new ReadOuputFileProperties();
			Properties prop=outputfile.OpenOutputFile();
			prop.clear();
			prop.setProperty("Created_Foler_Name", Foldername);
			prop.store(new FileOutputStream(".\\src\\main\\java\\Utilities\\OutPutFile.properties"),null);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
}
