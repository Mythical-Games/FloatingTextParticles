package com.mythicalgames.ft.config;

import eu.okaeri.configs.OkaeriConfig;
import java.util.HashMap;
import java.util.Map;

public class FloatingTextConfig extends OkaeriConfig {
    private Map<String, FloatingText> floatingtexts = new HashMap<>();

    public Map<String, FloatingText> getFloatingtexts() {
        return floatingtexts;
    }

    public void setFloatingtexts(Map<String, FloatingText> floatingtexts) {
        this.floatingtexts = floatingtexts;
    }
}

