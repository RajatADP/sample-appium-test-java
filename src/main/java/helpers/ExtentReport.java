package helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.HashMap;
import java.util.Map;

public class ExtentReport {
    static ExtentReports extent;
    final static String filePath = "report.html";
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
            htmlReporter.config().setDocumentTitle("Appium-Mobile-Suite");
            htmlReporter.config().setReportName("Mobile-Suite-Report");
            htmlReporter.config().setTheme(Theme.STANDARD);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest test = getReporter().createTest(testName, description);
        extentTestMap.put((int) Thread.currentThread().getId(), test);

        return test;
    }
}
