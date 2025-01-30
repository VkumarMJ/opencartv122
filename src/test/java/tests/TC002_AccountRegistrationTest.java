package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.SuccessRegPage;
import testBase.BaseClass;

public class TC002_AccountRegistrationTest extends BaseClass
{
	
	@Test(groups= {"Regression"})
	public void verify_AccountRegistration()
	{
		try
		{
			
			HomePage hp = new HomePage(driver);			
			AccountRegistrationPage acp =hp.ClickRegister();
			Faker faker= new Faker();
			SuccessRegPage srp = acp.fillPersonalDetails(faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),faker.phoneNumber().phoneNumber(),faker.internet().password());
			String successmsg =srp.getRegSuccessMsg();
			if(successmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
				System.out.println("Test failed");
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}		
	}
}
