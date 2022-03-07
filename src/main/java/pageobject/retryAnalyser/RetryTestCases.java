package pageobject.retryAnalyser;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import pageobject.DataAndPropertyUtils.ExtentReportsUtils;
import pageobject.factory.Factory;
import pageobject.restapiUtilites.BaseTest;

//import static pageobject.restapiUtilites.BaseTest.maxTry;
import static pageobject.restapiUtilites.BaseTest.report;
import static pageobject.restapiUtilites.RestBase.httpDelete;

public class RetryTestCases implements IRetryAnalyzer {

    public static int triedCount = 0;
    private int Retrycount = 0;
    private static int maxTry = 2;

    @Override
    public boolean retry(ITestResult result) {
        System.out.println("Inside the retry logic out side if and retry count is "+Retrycount+" and result is "+result.getStatus());
        if (!result.isSuccess()) {
            if (Retrycount < maxTry) {
                System.out.println("Inside the Retry Logic if conditions and retry count is "+Retrycount);
                Retrycount++;
                setCount(Retrycount);
                return true;
            } else {
                System.out.println("setting the count to zero");
                setCount(0);
            }
        }
        else
        {
            setCount(0);
        }

        return false;
    }

    public static void setCount(int count)
    {
        triedCount=count;
    }

    public int getTriedCount()
    {
        return triedCount;
    }
}
