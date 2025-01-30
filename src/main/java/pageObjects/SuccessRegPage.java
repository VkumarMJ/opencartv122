package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessRegPage extends BasePage
{
	public SuccessRegPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement regSuccessMsg;
	
	
	public String getRegSuccessMsg()
	{
		try
		{
			return regSuccessMsg.getText();
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
}
