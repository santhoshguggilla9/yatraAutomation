package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final Logger log = Logger.getLogger(BasePage.class);
    protected WebDriver driver;
        protected WebDriverWait wait;

        public BasePage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Customize wait as needed
        }

        /**
         * Waits for an element to be clickable, then clicks on it.
         * @param locator By locator of the element
         */
        public void click(By locator) {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            log.info("successfully clicked on offers link");
        }

        /**
         * Waits for an element to be visible, then sends text to it.
         * @param locator By locator of the element
         * @param text Text to be sent to the element
         */
        public void sendKeys(By locator, String text) {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
        }

        /**
         * Waits explicitly until the given condition is met.
         * @param locator By locator of the element
         */
        public void waitForElementToBeVisible(By locator) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
    }


