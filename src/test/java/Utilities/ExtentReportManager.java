package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener  {
	
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	
	public void onStart(ITestContext testContext) {
		
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+ timestamp +".html";
		
		sparkReporter =new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the file
		
		
		sparkReporter.config().setDocumentTitle("OrangeHrm Application Report");//title of the report
		sparkReporter.config().setReportName("Orange HRM Functional Testing");//Name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OrangHRM");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("submodule", "customers");
		extent.setSystemInfo("Environment", "QA");
		
		
		//String os =testContext.getCurrentXmlTest().getParameter("os");
		//extent.setSystemInfo("operating System", os);
		
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
			
		}
		
		
		
		
	}
	
	
	public void onTestSuccess(ITestResult result)
	{
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.PASS, result.getName()+"got succesfully executed");
	}
	
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
		try {
			String imgpath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public void onTestSkipped(ITestResult result) {
		
		
		test=extent.createTest(result.getTestClass().getName());
		
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
		
	}
	
	public void onFinish(ITestContext testcontext) {  
		
		
		extent.flush();
		
		
		String pathofExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathofExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
		
		
		

	
	
	
	
	
	


