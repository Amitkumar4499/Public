package Resources;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int Counter = 0;
    int RetryLimit = 3;

    public boolean retry(ITestResult result) {
	if (Counter < RetryLimit) {
	    Counter++;
	    return true;
	}
	return false;
    }
}
