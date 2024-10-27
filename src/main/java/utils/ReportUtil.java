package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtil {
    private static ExtentReports extent; // Instance of ExtentReports to generate the report
    private static ExtentTest test; // Instance of ExtentTest to log individual test cases

    /**
     * Initializes Extent Reports with the specified report path.
     *
     * @param reportPath The path where the report will be saved.
     */
    public static void initReport(String reportPath) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath); // Create a Spark reporter
        sparkReporter.config().setDocumentTitle("Automation Test Report"); // Set the document title
        sparkReporter.config().setReportName("Test Execution Report"); // Set the report name
        sparkReporter.config().setTheme(Theme.STANDARD); // Set the theme for the report

        extent = new ExtentReports(); // Initialize ExtentReports
        extent.attachReporter(sparkReporter); // Attach the Spark reporter
        extent.setSystemInfo("Environment", "QA"); // Set system information
        extent.setSystemInfo("Tester", "Sushma"); // Set tester name
    }

    /**
     * Starts logging for a new test case.
     *
     * @param testName The name of the test case to be logged.
     */
    public static void startTest(String testName) {
        test = extent.createTest(testName); // Create a new test entry in the report
        LogUtil.info("Started test: " + testName); // Log the test start
    }

    /**
     * Logs informational messages to the report.
     *
     * @param message The message to be logged.
     */
    public static void logInfo(String message) {
        test.info(message); // Log the message in the report
        LogUtil.info(message); // Log the message in the console
    }

    /**
     * Logs passed status messages to the report.
     *
     * @param message The message to be logged as passed.
     */
    public static void logPass(String message) {
        test.pass(message); // Log the message as passed in the report
        LogUtil.info(message); // Log the message in the console
    }

    /**
     * Logs failed status messages to the report.
     *
     * @param message The message to be logged as failed.
     */
    public static void logFail(String message) {
        test.fail(message); // Log the message as failed in the report
        LogUtil.error(message); // Log the message as an error in the console
    }

    /**
     * Adds a screenshot to the report.
     *
     * @param imagePath The path to the screenshot image.
     */
    public static void addScreenshot(String imagePath) {
        test.addScreenCaptureFromPath(imagePath); // Add the screenshot to the report
        logInfo("Screenshot added to report: " + imagePath); // Log the addition of the screenshot
    }

    /**
     * Flushes the report and saves it to the specified location.
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush(); // Flush the report data to the file
        }
        LogUtil.info("Report flushed and saved."); // Log the flushing action
    }
}
