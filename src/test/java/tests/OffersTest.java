package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.OffersPage;
import utils.ConfigReader;
import utils.ExcelUtils;
import utils.ScreenshotUtil;

public class OffersTest extends BaseTest {
    // Instance of ExcelUtils to read data from Excel files
    private ExcelUtils excelUtils = new ExcelUtils();

    // Instance of ConfigReader to access configuration properties
    private ConfigReader configReader = new ConfigReader();

    /**
     * Test method to validate the offers page functionality.
     */
    @Test
    public void testOffers() {
        // Retrieve the expected title and banner text from the Excel file
        String title = excelUtils.getExcelData("Sheet1", 1, 0);
        String bannerText = excelUtils.getExcelData("Sheet2", 1, 0);

        // Initialize page objects for HomePage and OffersPage
        HomePage homePage = new HomePage(driver);
        OffersPage offersPage = new OffersPage(driver);

        // Navigate to the Offers page
        homePage.navigateToOffers();

        // Validate the page title and banner logo against expected values
        offersPage.validatePageTitle(title);
        offersPage.validateBannerLogo(bannerText);

        // Create an instance of ScreenshotUtil to take screenshots
        ScreenshotUtil utils = new ScreenshotUtil(driver); // Pass initialized driver

        // Take a screenshot of the offers page
        utils.takeScreenshot("screenShot");

        // Click on the Holidays tab to view holiday packages
        offersPage.clickOnHolidaysTab();

        // List the holiday packages available on the page
        offersPage.listHolidayPackages();
    }
}
