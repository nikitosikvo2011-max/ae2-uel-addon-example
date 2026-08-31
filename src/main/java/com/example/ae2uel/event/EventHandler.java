package com.example.ae2uel.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ============================================================
 * AE2 UEL Addon - Event Handler
 * ============================================================
 *
 * Manages all event listeners for the mod.
 * This includes:
 * - Tick events (client and server)
 * - Player events
 * - World events
 * - AE2-specific events
 *
 * To add new event handlers:
 * 1. Create a new method with @SubscribeEvent
 * 2. Call EventHandler.registerEventHandlers() in AE2UELAddon.init()
 */
public class EventHandler {
    private static final Logger LOGGER = LogManager.getLogger("ae2uel-addon");

    /**
     * Register all event handlers
     */
    public static void registerEventHandlers() {
        LOGGER.info("Registering event handlers...");
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
        MinecraftForge.EVENT_BUS.register(new ServerTickHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        LOGGER.info("Event handlers registered successfully");
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            // Handle client-side tick logic here
        }
    }
}
