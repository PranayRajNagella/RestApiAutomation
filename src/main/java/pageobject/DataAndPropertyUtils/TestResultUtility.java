package pageobject.DataAndPropertyUtils;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static pageobject.restapiUtilites.BaseTest.report;

public class TestResultUtility {

    protected static ArrayList<String> passedTestCases=new ArrayList<>();
    protected static ArrayList<String> failedTestCases=new ArrayList<>();

    public static List<String> setResults(String Testcases)
    {
        List<String> testCases=new ArrayList <String>();
        if(Testcases.contains(",")) {
            testCases=new LinkedList<>(Arrays.asList(Testcases.split(",")));
        }
        else
        {
            testCases.add(Testcases);
        }
        return testCases;
    }

    public static void writePassedTestResult(List<String> passedTestFromATest)
    {
        passedTestCases.addAll(passedTestFromATest);
      //  System.out.println(passedTestFromATest+" TestResultUtility printing ");
    }

    public static void writeFailedTestResult(List<String> failedTestFromATest)
    {
        failedTestCases.addAll(failedTestFromATest);
       // System.out.println(failedTestCases+" TestResultUtility printing");
    }


}
