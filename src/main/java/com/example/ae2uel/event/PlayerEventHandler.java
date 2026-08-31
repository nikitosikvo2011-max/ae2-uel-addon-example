package com.example.ae2uel.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles player-related events
 */
public class PlayerEventHandler {
    private static final Logger LOGGER = LogManager.getLogger("ae2uel-addon");

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        LOGGER.debug("Player logged in: {}", event.player.getName());
        // Handle player login logic here
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        LOGGER.debug("Player logged out: {}", event.player.getName());
        // Handle player logout logic here
    }
}
