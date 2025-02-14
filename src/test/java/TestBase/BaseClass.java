package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import PageObject.LoginPage;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	

	
	
	@SuppressWarnings("deprecation")
	@Parameters({"browser", "Operating System"})
	@BeforeClass(groups = {"sanity", "regression", "master"})
	public void setup(String br, String os) throws IOException {
	    FileReader file = new FileReader("./src/test/resources/config.properties");
	    p = new Properties();
	    p.load(file);

	    System.out.println("Execution Environment: " + p.getProperty("execution_env"));
	    System.out.println("Browser: " + br);
	    System.out.println("Operating System: " + os);

	    logger = LogManager.getLogger(this.getClass());

	    if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
	        System.out.println("Initializing Remote WebDriver...");
	        DesiredCapabilities capabilities = new DesiredCapabilities();

	        if (os.equalsIgnoreCase("windows")) {
	            capabilities.setPlatform(Platform.WIN11);
	        } else if (os.equalsIgnoreCase("linux")) {
	            capabilities.setPlatform(Platform.LINUX);
	        } else {
	            System.out.println("No matching OS");
	            return;
	        }

	        switch (br.toLowerCase()) {
	            case "chrome":
	                capabilities.setBrowserName("chrome");
	                break;
	            case "edge":
	                capabilities.setBrowserName("edge");
	                break;
	            default:
	                System.out.println("No matching browser");
	                return;
	        }

	        driver = new RemoteWebDriver(new URL("http://192.168.231.51:4444"), capabilities);
	    } else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
	        System.out.println("Initializing Local WebDriver...");
	        switch (br.toLowerCase()) {
	            case "chrome":
	                driver = new ChromeDriver();
	                break;
	            case "firefox":
	                driver = new FirefoxDriver();
	                break;
	            case "edge":
	                driver = new EdgeDriver();
	                break;
	            default:
	                System.out.println("No matching browser");
	                return;
	        }
	    }

	    driver.manage().window().maximize();
	    driver.get(p.getProperty("appurl"));
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}





	public String captureScreen(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}