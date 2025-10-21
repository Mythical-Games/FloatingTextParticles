package com.mythicalgames.ft.config;

import org.joml.Vector3f;

import eu.okaeri.configs.OkaeriConfig;

public class FloatingText extends OkaeriConfig {
    private String world;
    private float x;
    private float y;
    private float z;
    private String text;

    public FloatingText() {} // Required

    public FloatingText(String world, Vector3f position, String text) {
        this.world = world;
        this.x = position.x();
        this.y = position.y();
        this.z = position.z();
        this.text = text;
    }

    public String getWorld() {
        return world;
    }

    public Vector3f getPosition() {
        return new Vector3f(x, y, z);
    }

    public String getText() {
        return text;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPosition(Vector3f position) {
        this.x = position.x();
        this.y = position.y();
        this.z = position.z();
    }
}


