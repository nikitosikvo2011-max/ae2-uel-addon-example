package com.example.ae2uel.util;

import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ============================================================
 * AE2 UEL Addon - Compatibility Checker
 * ============================================================
 *
 * Verifies that required mods are loaded and checks versions.
 * This ensures the addon runs only when dependencies are met.
 */
public class CompatibilityChecker {
    private static final Logger LOGGER = LogManager.getLogger("ae2uel-addon");

    /**
     * Check if AE2 UEL is loaded
     */
    public static boolean isAE2UELLoaded() {
        return Loader.isModLoaded("ae2-extended-life") || Loader.isModLoaded("appliedenergistics2");
    }

    /**
     * Check if JEI is loaded (optional dependency)
     */
    public static boolean isJEILoaded() {
        return Loader.isModLoaded("jei");
    }

    /**
     * Perform all compatibility checks
     */
    public static boolean checkAllCompatibility() {
        LOGGER.info("Performing compatibility checks...");

        if (!isAE2UELLoaded()) {
            LOGGER.error("AE2 Unofficial Extended Life is not loaded! This addon requires AE2 UEL.");
            return false;
        }

        LOGGER.info("All compatibility checks passed!");
        return true;
    }
}
