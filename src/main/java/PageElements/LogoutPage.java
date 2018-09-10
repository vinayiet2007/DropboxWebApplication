/**
 * 
 */
package PageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Drivers.Driver;

/**
 * @author vinay
 *
 */
public class LogoutPage extends Driver
{
WebDriverWait ExplicitWait;
	
	/*
	 	Login Page Elements declared
	*/

	@FindBy(className="mc-avatar-image")
	WebElement Logo_After_Login;
	
	@FindBy(xpath="//a[contains(text(),'Sign out')]")
	WebElement Button_logout;
	/*
 		Login Page Elements Initialized with PageFactory
	*/
	
	public LogoutPage()
	{
		PageFactory.initElements(driver, this);
		ExplicitWait = new WebDriverWait(driver,20);
	}
	
	public boolean Logout() throws Exception
	{
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Logo_After_Login)).click();
		Actions action = new Actions(driver); 
		action.moveToElement(Button_logout).click().build().perform();
		//Thread.sleep(3000);
		//ExplicitWait.until(ExpectedConditions.elementToBeClickable(Button_logout)).click();
		Thread.sleep(3000);
		String Current_Url=driver.getCurrentUrl().toString();
		if(Current_Url.contains("logout"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
