package com.example.ae2uel.api;

/**
 * ============================================================
 * AE2 UEL Addon - Public API
 * ============================================================
 *
 * This package contains the public API for this addon.
 * Other mods can depend on this addon and use these classes.
 *
 * To use this addon's API in your mod:
 * 1. Add dependency: modCompile 'curse.maven:...:...'
 * 2. Import classes from com.example.ae2uel.api
 * 3. Use the public methods
 *
 * WARNING: Methods and classes in this package are considered stable.
 * Breaking changes will only occur in major version updates.
 */
public class AE2UELAddonAPI {
    /**
     * Get the addon version
     */
    public static String getVersion() {
        return "1.0.0";
    }

    /**
     * Check if addon is loaded
     */
    public static boolean isLoaded() {
        return true;
    }
}
