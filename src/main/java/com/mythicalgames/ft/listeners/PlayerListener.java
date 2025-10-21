package com.mythicalgames.ft.listeners;

import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
import org.allaymc.api.eventbus.event.player.PlayerQuitEvent;

import com.mythicalgames.ft.FloatingTextManager;

public class PlayerListener {

    private FloatingTextManager manager;

    public PlayerListener(FloatingTextManager manager) {
        this.manager = manager;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        EntityPlayer player = event.getPlayer();

        // Add the joining player as a viewer to all active floating texts
        manager.showAllToPlayer(player);
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        EntityPlayer player = event.getPlayer();

        // Remove the quitting player from all floating texts' viewers
        manager.hideAllFromPlayer(player);
    }
}

