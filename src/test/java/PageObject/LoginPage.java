package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@placeholder='Username']")
	 WebElement   usernametextfeild;
	
	
	@FindBy(xpath="//input[@placeholder='Password']")
     WebElement passwordtextfeild;
	
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement loginpage;
	
	
	
	public void username(String username) {
		usernametextfeild.sendKeys(username);
	}
	
	public void password(String password) {
		passwordtextfeild.sendKeys(password);
	}
	
	
	public void loginbutton()
	{
		loginpage.click();
	}
	
}
