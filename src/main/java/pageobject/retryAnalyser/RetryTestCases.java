package pageobject.retryAnalyser;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


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
