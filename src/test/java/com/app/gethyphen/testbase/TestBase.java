package com.app.gethyphen.testbase;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.app.gethyphen.config.Config;

public class TestBase {

	public static WebDriver driver;

	public WebDriver getDriver()
	{
		return driver;
	}
	public void setDriver(WebDriver driver)
	{
		this.driver= driver;
	}

	public TestBase(WebDriver driver) {
		this.driver = driver;
	}
	public TestBase(){}

	@BeforeSuite
	public void beforeSuite() throws Exception{
		Config.setProperties("Production");
		setBrowser();
	}


	public void setBrowser() throws MalformedURLException
	{
		String browser = Config.browser;
		String mode = Config.mode;

		if (browser.equalsIgnoreCase("FIREFOX") && mode.equals("nogrid"))
		{
			String path = System.getProperty("user.dir") + "\\driver\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", path);
			setDriver(new FirefoxDriver());
			getDriver().manage().window().maximize();
		}
		else if (browser.equalsIgnoreCase("CHROME") && mode.equals("nogrid"))
		{ 
			String path = "D:\\\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", path);
			setDriver(new ChromeDriver());
			getDriver().manage().window().maximize();
		}
		else if (browser.equalsIgnoreCase("IE") && mode.equals("nogrid"))
		{
			String path = System.getProperty("user.dir")
					+ "\\driver\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", path);
			setDriver(new InternetExplorerDriver());
			getDriver().manage().window().maximize();
		}
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
	    try
	 {
	    if(result.getStatus() == ITestResult.SUCCESS)
	    {
	    	//screenShot(result);
	    }
	    else if(result.getStatus() == ITestResult.FAILURE)
	    {

	    	screenShot(result);
	    }
	     else if(result.getStatus() == ITestResult.SKIP ){
	    	 screenShot(result);
	    }
	}
	   catch(Exception e)
	   {
	     e.printStackTrace();
	   }

	}
	


	  @AfterSuite public void closeBrowser() { getDriver().quit(); }


	public String screenShot(ITestResult result) throws Exception {
		String folder =  System.getProperty("user.dir")
				+ System.getProperty("file.separator")
				+ "screenshots";		
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		String filePath = folder + System.getProperty("file.separator")
				+ this.getClass()
				.getName()
				.substring(
						this.getClass().getName()
						.lastIndexOf(".") + 1)
				+ "_"
				+ result.getMethod().getMethodName()
				+ "_"
				+ new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime())+ "_"
				+ getStatusString(result.getStatus())
				+ ".png";
		FileUtils.copyFile(	screenshot,	new File(filePath));
		return filePath;
	}

	private String getStatusString(int testResultStatus) {
		switch (testResultStatus) {
		case ITestResult.SUCCESS:
			return "PASS";
		case ITestResult.FAILURE:
			return "FAIL";
		case ITestResult.SKIP:
			return "SKIP";
		case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
			return "SUCCESS_PERCENTAGE_FAILURE";
		}
		return null;
	}
	
}
