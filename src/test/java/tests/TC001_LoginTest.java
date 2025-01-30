package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC001_LoginTest extends BaseClass
{
	//WebDriver driver;
	@Test(dataProvider = "getData",groups = "sanity")
	public void verifyLogin(HashMap<String,String> data)
	{
		try
		{	
			HomePage hp = new HomePage(driver);
			LoginPage lp= hp.ClickLogin();			
			MyAccountPage myaccpage = lp.loginOpenCart(data.get("username"), data.get("password"));
			boolean myaccpageexist =myaccpage.isMyAccountPageExits();
			
			if(data.get("res").equalsIgnoreCase("valid"))
			{	
				if(myaccpageexist==true)
				{
					myaccpage.clickLogout();
					Assert.assertTrue(true);					
				}
				else
				{
					Assert.assertTrue(false);
					//System.out.println("test failed.");
				}
			}
			if(data.get("res").equalsIgnoreCase("invalid"))
			{
				if(myaccpageexist==true)
				{
					myaccpage.clickLogout();
					Assert.assertTrue(false);					
				}
				else
				{
					Assert.assertTrue(true);
				}
				
			}
			//myaccpage.clickLogout();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data =getJsonDataToMap("./testData/Login.json");
		
		Object[][] testData = new Object[data.size()][];
		for (int i = 0; i < data.size(); i++) {
	        testData[i] = new Object[] 
	        {
	        	data.get(i)
	        };
	    }
		
		return testData;
		//return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)}};
	}
}
