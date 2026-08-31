package com.example.ae2uel.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles server-side tick events
 */
public class ServerTickHandler {
    private static final Logger LOGGER = LogManager.getLogger("ae2uel-addon");

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            // Handle server-side tick logic here
        }
    }
}
