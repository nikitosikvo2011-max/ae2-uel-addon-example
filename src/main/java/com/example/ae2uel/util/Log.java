package com.example.ae2uel.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ============================================================
 * AE2 UEL Addon - Logging Utilities
 * ============================================================
 *
 * Provides convenient logging methods for the addon.
 * Usage:
 * - Log.info("Message")
 * - Log.warn("Warning")
 * - Log.error("Error", exception)
 * - Log.debug("Debug message")
 */
public class Log {
    private static final Logger LOGGER = LogManager.getLogger("ae2uel-addon");

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void info(String message, Object... params) {
        LOGGER.info(message, params);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void warn(String message, Object... params) {
        LOGGER.warn(message, params);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void debug(String message, Object... params) {
        LOGGER.debug(message, params);
    }
}
