package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    public WebDriver driver;
    public Properties prop;
    protected static ExtentReports extent = ExtentReport.createintance();
    protected static ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();
    public String baseURI = "http://localhost:3000/posts";

    public void InitiateBrowser(String browser) throws Exception {
	System.setProperty("Configuration", "../Testing/src/main/resources/Configuration.properties");
	FileInputStream fis = new FileInputStream(System.getProperty("Configuration"));
	prop = new Properties();
	prop.load(fis);
	if (browser.equalsIgnoreCase("Chrome")) {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	} else if (browser.equalsIgnoreCase("Firefox")) {
	    WebDriverManager.chromedriver().setup();
	    driver = new FirefoxDriver();
	    driver.manage().window().maximize();
	}
    }

    public String takesScreenshot(WebDriver driver, String methodName) {
	String fileName = getscreenshotName(methodName);
	String directory = System.getProperty("user.dir") + "/Screenshots/";
	new File(directory).mkdirs();
	String path = directory + fileName;
	File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	try {
	    FileUtils.copyFile(screenShot, new File(path));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return path;
    }

    public static String getscreenshotName(String methodName) {
	Date d = new Date();
	return methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
    }
}
