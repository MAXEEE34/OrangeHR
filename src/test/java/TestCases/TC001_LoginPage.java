package TestCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObject.LoginPage;
import TestBase.BaseClass;

public class TC001_LoginPage extends BaseClass{
	
	

	
	
	@Test(groups= {"regression","master"})
	public void loginpage()
	{
	logger.info(" starting TestCase 1");	
	try {	
		LoginPage	loginpage=new LoginPage(driver);
		
		loginpage.username(p.getProperty("username"));
		loginpage.password(p.getProperty("password"));
		loginpage.loginbutton();
		
		
		
		
	} catch (Exception e) {
		logger.debug("exception occured the test case has been failed 1");
		Assert.fail();
		
	}
	logger.info("test case ended 1");	
	
	}
	
	
	
	
	
	
	
}

