package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private WebDriver driver; // WebDriver instance to take screenshots

    /**
     * Constructor to initialize ScreenshotUtil with a WebDriver instance.
     *
     * @param driver The WebDriver instance to use for taking screenshots.
     */
    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver; // Assign the passed WebDriver to the class instance
    }

    /**
     * Takes a screenshot of the current browser window and saves it with a timestamp.
     *
     * @param fileName The base name for the screenshot file (without extension).
     */
    public void takeScreenshot(String fileName) {
        // Check if the WebDriver is initialized
        if (driver == null) {
            LogUtil.info("WebDriver is not initialized. Cannot take screenshot."); // Log if the driver is not initialized
            return; // Exit the method if the driver is not available
        }

        // Create a timestamped file name for the screenshot
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fullFileName = "src/main/resources/screenshots/" + fileName + "_" + timeStamp + ".png";

        // Take the screenshot as a file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            // Save the screenshot to the specified path
            FileUtils.copyFile(screenshot, new File(fullFileName));
            LogUtil.info("Screenshot saved: " + fullFileName); // Log the successful save
        } catch (IOException e) {
            // Log an error message if saving the screenshot fails
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
