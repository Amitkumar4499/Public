package Resources;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    private static ExtentReports extent;

    public static ExtentReports createintance() {
	String fileName = getReportName();
	String Directory = System.getProperty("user.dir") + "/reports/";
	new File(Directory).mkdirs();
	String path = Directory + fileName;
	ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
	htmlReporter.config().setEncoding("utf-8");
	htmlReporter.config().setDocumentTitle("Automation report");
	htmlReporter.config().setReportName("Automationreport");
	htmlReporter.config().setTheme(Theme.DARK);
	extent = new ExtentReports();
	extent.setSystemInfo("Orgnization", "CVT");
	extent.setSystemInfo("Browser", "Chrome");
	extent.setSystemInfo("Product", "ProdPort");
	extent.setSystemInfo("Environment", "Demo");
	extent.attachReporter(htmlReporter);
	return extent;
    }

    public static String getReportName() {
	Date d = new Date();
	String fileName = "Automation_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	return fileName;
    }
}
