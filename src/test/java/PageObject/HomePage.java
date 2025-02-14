package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
	
	public  HomePage(WebDriver driver) {
		super(driver);
		
	}
		
		public boolean ismypageexist()
		{
			boolean result = driver.findElement(By.xpath("//p[text()='My Actions']")).isDisplayed();
			return result;
		}
}

