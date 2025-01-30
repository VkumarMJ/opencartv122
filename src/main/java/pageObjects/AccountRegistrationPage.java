package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegistrationPage extends BasePage
{
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkagree;
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	
	public SuccessRegPage fillPersonalDetails(String fname,String lname,String email,String telephone,String pwd)
	{
		txtFirstName.sendKeys(fname);
		txtLastName.sendKeys(lname);
		txtEmail.sendKeys(email);
		txtTelephone.sendKeys(telephone);
		txtPassword.sendKeys(pwd);
		txtConfirmPassword.sendKeys(pwd);
		chkagree.click();
		btnContinue.click();
		SuccessRegPage srp = new SuccessRegPage(driver);
		return srp;
	}
	
}
