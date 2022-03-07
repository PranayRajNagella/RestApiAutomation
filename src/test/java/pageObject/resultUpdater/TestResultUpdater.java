package pageObject.resultUpdater;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobject.DataAndPropertyUtils.TestResultUtility;

import java.util.HashSet;

public class TestResultUpdater extends TestResultUtility {

    @Test()
    public void testResultUpdate()
    {
        HashSet<String> passedTestSet=new HashSet<>(passedTestCases);
        HashSet<String> failedTestSet=new HashSet<>(failedTestCases);
        passedTestSet.removeAll(failedTestSet);
        Reporter.log(passedTestSet.toString()+"List passed TestCases");
        Reporter.log(failedTestSet.toString()+"List failed TestCases");
    }

}
