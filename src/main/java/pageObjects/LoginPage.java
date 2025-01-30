package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@value='Login']") WebElement btnLogin;
	
	public MyAccountPage loginOpenCart(String username,String password)
	{
		txtEmail.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
		MyAccountPage mcp= new MyAccountPage(driver);
		return mcp;
		
	}
}
