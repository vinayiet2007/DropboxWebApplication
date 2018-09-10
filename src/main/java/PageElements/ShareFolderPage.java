/**
 * 
 */
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
public class ShareFolderPage extends Driver
{
	/*
	 * All Page elements declared here
	 */
	WebDriverWait ExplicitWait;
	
	@FindBy(xpath="/html/body/div[4]/div/div[6]/div[2]/div/div/main/div[1]/div/div/div[1]/table")
	WebElement Table_folderlist;
	
	@FindBy(xpath="//a[contains(text(),'Files')]")
	WebElement Link_files;
	
	@FindBy(className="mc-checkbox-input")
	WebElement Input_selectfolder;
	
	@FindBy (xpath="//div[contains(@class,'primary-action-menu__button-wrapper')]//span[contains(text(),'Share')]")
	WebElement Button_sharefolder;
	
	@FindBy(id="unified-share-modal-contacts-tokenizer")
	WebElement Textbox_shareemail;
	
	@FindBy(xpath="//div[contains(@class,'u-l-fr')]/button[contains(text(),'Share')]")
	WebElement Button_sharefolderwithothers;
	
	@FindBy(xpath="/html/body/div[1]/div/div/div/p")
	WebElement Popup_messagefoldershared;
	
	public ShareFolderPage()//Page Elements initialized
	{
		PageFactory.initElements(driver, this);
		ExplicitWait = new WebDriverWait(driver,20);//explicit wait is 20 secs
	}
	
	public boolean Selectandsharefolder(String Foldername, String Email) throws Exception
	{
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Link_files)).click();
		List<WebElement> Tablerows=driver.findElements(By.xpath("/html/body/div[4]/div/div[6]/div[2]/div/div/main/div[1]/div/div/div[1]/table/tbody/tr/td[1]"));
		
		String Xpathbeforetablerow="/html/body/div[4]/div/div[6]/div[2]/div/div/main/div[1]/div/div/div[1]/table/tbody/tr[";
		String Xpathaftertablerow="]//div[@class='brws-file-name-element']/span";
		String Xpathaftertablecol="]//span[@class='mc-checkbox-border']";
		System.out.println(Tablerows.size());
		for (int i=1;i<=Tablerows.size();i++)//Verify each row in the folder table
		{
			if(driver.findElement(By.xpath(Xpathbeforetablerow + (i) + Xpathaftertablerow )).getText().contains(Foldername))
			{
				 Actions action = new Actions(driver); 
				 action.moveToElement(driver.findElement(By.xpath(Xpathbeforetablerow + (i) + Xpathaftertablecol ))).click().build().perform();
				 Thread.sleep(3000);
				 break;
			}
		}
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Button_sharefolder)).click();
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Textbox_shareemail)).sendKeys(Email);
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Button_sharefolderwithothers)).click();
		String popupmessage=null;
		try
		{
			while(Popup_messagefoldershared.isDisplayed())
			{
				popupmessage=ExplicitWait.until(ExpectedConditions.elementToBeClickable(Popup_messagefoldershared)).getText();
				if (popupmessage.contains("Shared"))
				{
					return true;
				}
			}
		}
		catch(StaleElementReferenceException ex)
		{
			popupmessage=ExplicitWait.until(ExpectedConditions.elementToBeClickable(Popup_messagefoldershared)).getText();
			if (popupmessage.contains("Shared"))
			{
				return true;
			}
		}
		
		return false;
		
		
	}
}
