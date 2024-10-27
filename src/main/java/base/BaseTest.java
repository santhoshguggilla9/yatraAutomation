package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;
import utils.LogUtil;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    private ConfigReader configReader = new ConfigReader();

    /**
     * This method is executed before any test class.
     * It initializes the WebDriver based on the browser type specified in the configuration file,
     * sets up default browser properties, navigates to the URL, and initializes reporting.
     */
    @BeforeClass
    public void setUp() {
        // Fetch the browser type from the configuration file
        String browser = configReader.getProperty("browser");

        // Initialize WebDriver based on the specified browser
        if ("edge".equalsIgnoreCase(browser)) {
            driver = new EdgeDriver();
            LogUtil.info("EdgeDriver initialized.");
        } else if("chrome".equalsIgnoreCase(browser)) {
            driver = new ChromeDriver();
            LogUtil.info("ChromeDriver initialized.");
        } else {
            LogUtil.info("No matching browser found, please check configuration.");
        }

        // Set default timeout for locating elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Maximize the browser window for better view
        driver.manage().window().maximize();

        // Navigate to the URL specified in the configuration file
        driver.get(configReader.getProperty("url"));

        // Initialize reporting setup
        setupReporting();
    }

    /**
     * Sets up ExtentReports for test reporting.
     * Configures the report theme, document title, and report name,
     * and sets system information like the tester's name.
     */
    public void setupReporting() {
        // Initialize ExtentSparkReporter with the report path from the config
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(configReader.getProperty("reportPath"));

        // Configure report theme and document title
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Yatra Test Automation Report");
        sparkReporter.config().setReportName("Yatra Test Report");

        // Attach the reporter to ExtentReports and set additional system information
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Sushma koli");

        // Create a test instance for logging within the report
        test = extent.createTest("Yatra Test - ");
        LogUtil.info("Extent Reports initialized");
    }

    /**
     * This method is executed after all tests in the class have run.
     * It closes the browser if open, flushes the extent report, and saves the results.
     */
    @AfterClass
    public void tearDown() {
        // Close the browser if the driver was initialized
        if (driver != null) {
            driver.quit();
            LogUtil.info("Browser closed successfully.");
        }

        // Flush and save the extent report
        if (extent != null) {
            extent.flush();
            LogUtil.info("Extent report flushed and saved.");
        }
    }
}
