package com.mythicalgames.ft;

import org.allaymc.api.debugshape.DebugText;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;

import com.mythicalgames.ft.config.FloatingText;
import com.mythicalgames.ft.config.FloatingTextConfig;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class FloatingTextManager {

    private final Map<String, DebugText> activeTexts = new HashMap<>();

    public void spawnText(String id, FloatingText ft) {
        DebugText debugText = new DebugText(
            ft.getPosition(),
            Color.WHITE,
            ft.getText()
        );

        List<EntityPlayer> onlinePlayers = getOnlinePlayers();
        for (EntityPlayer player : onlinePlayers) {
            debugText.addViewer(player);
        }

        activeTexts.put(id, debugText);
    }

    public void removeText(String id) {
        DebugText text = activeTexts.remove(id);
        if (text != null) {
            for (EntityPlayer player : getOnlinePlayers()) {
                text.removeViewer(player);
            }
        }
    }

    public void loadAll(FloatingTextConfig config) {
        for (Map.Entry<String, FloatingText> entry : config.getFloatingtexts().entrySet()) {
            spawnText(entry.getKey(), entry.getValue());
        }
    }

    public void clearAll() {
        for (DebugText text : activeTexts.values()) {
            for (EntityPlayer player : getOnlinePlayers()) {
                text.removeViewer(player);
            }
        }
        activeTexts.clear();
    }

    public void showAllToPlayer(EntityPlayer player) {
        for (DebugText text : activeTexts.values()) {
            text.addViewer(player);
        }
    }

    public void hideAllFromPlayer(EntityPlayer player) {
        for (DebugText text : activeTexts.values()) {
            text.removeViewer(player);
        }
    }

    private List<EntityPlayer> getOnlinePlayers() {
        return Server.getInstance().getPlayerManager().getPlayers().values().stream().collect(Collectors.toList());
    }
}

