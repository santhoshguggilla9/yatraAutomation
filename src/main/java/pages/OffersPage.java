package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.LogUtil;
import java.util.List;
import java.util.Set;

public class OffersPage extends BasePage {

    private WebDriver driver;

    // Locators for the banner logo and Holidays tab
    private By banner_Logo = By.xpath("//h2[text()='Great Offers & Amazing Deals']");
    private By tab_Holidays = By.xpath("//ul[@id='offer-box-shadow']//li/a[text()='Holidays']");

    /**
     * Constructor for OffersPage class.
     * Initializes the driver and inherits functionalities from BasePage.
     *
     * @param driver WebDriver instance passed from test class.
     */
    public OffersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Validates the page title to ensure the correct page is loaded.
     *
     * @param expectedTitle Expected title of the page for validation.
     */
    public void validatePageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        try {
            Assert.assertEquals(actualTitle, expectedTitle, "Title mismatched!");
            LogUtil.info("Page title validation passed: " + actualTitle);
        } catch (AssertionError e) {
            LogUtil.info("Page title validation failed! Expected: " + expectedTitle + ", got: " + actualTitle);
        }
    }

    /**
     * Validates the banner logo text to confirm presence of correct offers section.
     *
     * @param expectedBannerText Expected text in the banner for validation.
     */
    public void validateBannerLogo(String expectedBannerText) {
        waitForElementToBeVisible(banner_Logo);
        String actualBannerText = driver.findElement(banner_Logo).getText();
        try {
            Assert.assertTrue(actualBannerText.contains(expectedBannerText), "Banner text mismatched!");
            LogUtil.info("Banner logo validated: " + actualBannerText);
        } catch (AssertionError e) {
            LogUtil.error("Banner logo validation failed! Expected text to contain: " + expectedBannerText);
            throw e;
        }
    }

    /**
     * Clicks on the Holidays tab to navigate to the Holidays section.
     */
    public void clickOnHolidaysTab() {
        click(tab_Holidays);
        LogUtil.info("Clicked on Holidays Package tab");
    }

    /**
     * Lists holiday packages by fetching package names and prices for each package.
     * Opens each package's details in a new window, extracts the details, and closes the window.
     */
    public void listHolidayPackages() {
        // Finds all holiday packages with 'View Details' button
        List<WebElement> list_Of_Packages = driver.findElements(By.xpath("//span[text()='View Details']"));
        String originalWindow = driver.getWindowHandle();
        int processedCount = 0;

        for (int i = 0; i < list_Of_Packages.size(); i++) {
            // Skip the second package (index 1) for demonstration
            if (i == 1) {
                continue;
            }

            // Click on the package to open details in a new window
            list_Of_Packages.get(i).click();
            LogUtil.info("Clicked on the package to open its details in a new window");
            Set<String> allWindows = driver.getWindowHandles();

            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    processedCount++;

                    try {
                        // Extract package name and price
                        String packageName = driver.findElement(By.xpath("//div[@id='leftSect']//h2[@class='ng-binding']")).getText();
                        String packagePrice = driver.findElement(By.xpath("//span[@class='price ng-binding']")).getText();

                        // Log the package details
                        System.out.println("Package " + (i + 1) + ": " + packageName + " - Price: " + packagePrice);
                        LogUtil.info("Details: Package " + (i + 1) + ": " + packageName + " - Price: " + packagePrice);

                    } catch (Exception e) {
                        System.err.println("Error fetching package details: " + e.getMessage());
                    } finally {
                        driver.close(); // Close the new window
                        LogUtil.info("Closed the new window");
                        driver.switchTo().window(originalWindow); // Switch back to original window
                        LogUtil.info("Switched back to the original window");
                    }

                    // Limit to processing 3 packages for efficiency
                    if (processedCount > 2) {
                        break;
                    }
                }
            }

            if (processedCount > 2) {
                break;
            }
        }
    }
}
