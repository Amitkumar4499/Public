package Resources;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listeners extends BaseClass implements ITestListener {
    public void onTestStart(ITestResult result) {
	ExtentTest test = extent
		.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
	extenttest.set(test);
    }

    public void onTestSuccess(ITestResult result) {

	String logText = "<b>" + result.getMethod().getMethodName() + " -> Pass</b>";
	Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
	extenttest.get().log(Status.PASS, m);

    }

    public void onTestFailure(ITestResult result) {

	if (result.getStatus() == ITestResult.FAILURE) {

	    String execptionMessage = Arrays.toString(result.getThrowable().getStackTrace());

	    extenttest.get().fail("<details><summary><b><font color= red> Execption occured, click to see details:"
		    + " </font></b></summary>" + execptionMessage.replace(",", "<br>") + "</details>\n");

	    String path = takesScreenshot(driver, result.getMethod().getMethodName());

	    try {
		extenttest.get().fail("Screenshot of failure Method",
			MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	    } catch (Exception e) {
		extenttest.get().fail("test failed , cannnot attach screenshot");
	    }

	    String logText = "<b> " + result.getMethod().getMethodName() + " -> failed</b>";
	    Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
	    extenttest.get().log(Status.FAIL, m);

	}
    }

    public void onTestSkipped(ITestResult result) {

	String logText = "<b> " + result.getMethod().getMethodName() + " -> Skip</b>";
	Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
	extenttest.get().log(Status.SKIP, m);

    }

    public void onFinish(ITestContext context) {
	if (extent != null) {
	    extent.flush();
	}
    }
}
