package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLogin;
	
	public LoginPage ClickLogin()
	{
		lnkMyAccount.click();
		lnkLogin.click();
		LoginPage lp= new LoginPage(driver);
		return lp;
	}
	
	public AccountRegistrationPage ClickRegister()
	{
		lnkMyAccount.click();
		lnkRegister.click();
		AccountRegistrationPage acp = new AccountRegistrationPage(driver);
		return acp;
	}
	
	
}
