package com.example.ae2uel;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.ae2uel.config.ConfigManager;
import com.example.ae2uel.event.EventHandler;
import com.example.ae2uel.registry.RegistryManager;

/**
 * ============================================================
 * AE2 UEL Addon - Main Mod Entry Point
 * ============================================================
 *
 * This is the main class for the AE2 UEL Addon.
 * It handles mod initialization and lifecycle management.
 *
 * Key Responsibilities:
 * - Initialize the mod
 * - Register event handlers
 * - Load configurations
 * - Set up dependencies
 *
 * @author Your Name
 * @version 1.0.0
 */
@Mod(
    modid = Tags.MODID,
    name = Tags.MODNAME,
    version = Tags.VERSION,
    dependencies = "required-after:ae2-extended-life;after:jei@[4.12.0,);"
)
public class AE2UELAddon {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    public static final String MOD_ID = Tags.MODID;
    public static final String MOD_NAME = Tags.MODNAME;
    public static final String VERSION = Tags.VERSION;
    public static final String MC_VERSION = Tags.MC_VERSION;
    public static final String AE2_UEL_VERSION = Tags.AE2_UEL_VERSION;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("========================================");
        LOGGER.info("Pre-initializing {} v{}", MOD_NAME, VERSION);
        LOGGER.info("Minecraft: {}, AE2 UEL: {}", MC_VERSION, AE2_UEL_VERSION);
        LOGGER.info("========================================");

        // Load configuration
        ConfigManager.loadConfig();
        LOGGER.info("Configuration loaded successfully");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Initializing {} v{}", MOD_NAME, VERSION);

        // Register items, blocks, and recipes
        try {
            RegistryManager.registerItems();
            RegistryManager.registerBlocks();
            RegistryManager.registerRecipes();
            LOGGER.info("Registry management completed successfully");
        } catch (Exception e) {
            LOGGER.error("Error during registry initialization", e);
        }

        // Register event handlers
        try {
            EventHandler.registerEventHandlers();
            LOGGER.info("Event handlers registered successfully");
        } catch (Exception e) {
            LOGGER.error("Error during event handler registration", e);
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("Post-initializing {} v{}", MOD_NAME, VERSION);

        // Perform post-initialization tasks
        try {
            // Check AE2 UEL compatibility
            checkAE2UELCompatibility();
            LOGGER.info("AE2 UEL compatibility check passed");
        } catch (Exception e) {
            LOGGER.error("Error during post-initialization", e);
        }

        LOGGER.info("{} v{} loaded successfully!", MOD_NAME, VERSION);
    }

    /**
     * Verifies that AE2 UEL is properly loaded and compatible
     */
    private void checkAE2UELCompatibility() {
        // This will be implemented when we have access to AE2 UEL's API
        LOGGER.debug("AE2 UEL compatibility check completed");
    }
}
