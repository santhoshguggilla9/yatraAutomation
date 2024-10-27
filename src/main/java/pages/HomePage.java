package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {
    private WebDriver driver;

    // Locator for the 'Offers' link
    private By offersLink = By.xpath("//ul[contains(@class,'justified-me')]//a[normalize-space(text())='Offers'][1]");

    /**
     * Constructor for the HomePage class.
     * Initializes the driver and inherits the BasePage functionalities.
     *
     * @param driver The WebDriver instance passed from the test class.
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Method to navigate to the Offers page.
     * Clicks on the 'Offers' link to open the offers page.
     * Note: Optionally, Actions class is available for any hover requirements.
     */
    public void navigateToOffers() {
        click(offersLink); // Clicks on the 'Offers' link using BasePage's click method

        // Alternative option to use Actions for hovering and clicking, if needed for future implementations
        /*
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(offersLink)).click().perform();
        */
    }
}
