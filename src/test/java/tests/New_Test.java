package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class New_Test 

{
	@Test
	public void newtest()
	{
		boolean myaccpageexist =true;
		
			
			if(myaccpageexist==true)
			{
				Assert.assertTrue(true);					
			}
			else
			{
				Assert.assertTrue(false);
			}
		
	}

}
