package PageElements;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Drivers.Driver;

public class Loginpage extends Driver
{
	WebDriverWait ExplicitWait;
	
	/*
	 	Login Page Elements declared
	*/
	
	@FindBy(id="sign-up-in")
	WebElement Button_SignIn;
	
	@FindBy(name="login_email")
	WebElement Textbox_Loginemail;
	
	@FindBy(name="login_password")
	WebElement Textbox_Loginpassword;
	
	@FindBy(className="signin-text")
	WebElement Button_Login;
	
	@FindBy(className="mc-avatar-image")
	WebElement Logo_After_Login;
	/*
 		Login Page Elements Initialized with PageFactory
	*/
	
	public Loginpage()
	{
		PageFactory.initElements(driver, this);
		ExplicitWait = new WebDriverWait(driver,20);
	}
	
	
	public WebElement Login(String Email, String Password) throws InterruptedException
	{
		//SignIn button will be clicked
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Button_SignIn)).click();
		//Email entered
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Textbox_Loginemail)).sendKeys(Email);
		//Password entered
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Textbox_Loginpassword)).sendKeys(Password);
		//Login button clicked
		ExplicitWait.until(ExpectedConditions.elementToBeClickable(Button_Login)).click();
		
		return ExplicitWait.until(ExpectedConditions.elementToBeClickable(Logo_After_Login));
	}
	
}
