package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.HomePage;
import PageObject.LoginPage;
import TestBase.BaseClass;

public class TC002_loginTest extends BaseClass {
	
	@Test(groups= {"sanity","master"})
	public void loginverify()
	{
		
		try {

			logger.info(" starting TestCase 2");	
				
			
				LoginPage	loginpage=new LoginPage(driver);
				
				loginpage.username(p.getProperty("username"));
				loginpage.password(p.getProperty("password"));
				loginpage.loginbutton();
				
				
				            HomePage home = new HomePage(driver);
				           boolean result = home.ismypageexist();
				           
				           Assert.assertTrue(result);
				           
				           logger.info("ending test case 2");
		} catch (Exception e) {
			logger.info("test case failed 2");
			
			Assert.fail();
		}
			
			
			
	}
}

