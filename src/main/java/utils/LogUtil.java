package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    // Create a logger instance for this class
    private static final Logger logger = LogManager.getLogger(LogUtil.class);

    /**
     * Logs an informational message.
     *
     * @param message The message to be logged at the INFO level.
     */
    public static void info(String message) {
        logger.info(message); // Log the message at INFO level
    }

    /**
     * Logs an error message.
     *
     * @param message The message to be logged at the ERROR level.
     */
    public static void error(String message) {
        logger.error(message); // Log the message at ERROR level
    }
}
