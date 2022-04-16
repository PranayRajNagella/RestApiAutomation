package pageobject.DataAndPropertyUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import pageobject.restapiUtilites.BaseTest;

public class Utility extends BaseTest {

    public static void removeReport(int count, ExtentReports report, ExtentTest test, String MethodName)
    {
        if(count!=0){
            System.out.println(count+" inside the removeReport for the report ");
            report.removeTest(test);
            System.out.println("removed reports for" + MethodName+" for the count"+ count);
        }
    }
}
