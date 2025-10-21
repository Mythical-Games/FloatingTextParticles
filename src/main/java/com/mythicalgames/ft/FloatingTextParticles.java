package com.mythicalgames.ft;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;

import com.mythicalgames.ft.commands.FTCommand;
import com.mythicalgames.ft.config.FloatingTextConfig;
import com.mythicalgames.ft.listeners.PlayerListener;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;

@Slf4j
public class FloatingTextParticles extends Plugin {

    private FloatingTextConfig config;
    private FloatingTextManager manager;

    @Override
    public void onLoad() {
        config = ConfigManager.create(FloatingTextConfig.class, config -> {
        config.withConfigurer(new YamlSnakeYamlConfigurer());
        config.withBindFile(pluginContainer.dataFolder().resolve("config.yml"));
        config.withRemoveOrphans(true);
        config.saveDefaults();
        config.load(true);
        });
    
        manager = new FloatingTextManager();
    }

    @Override
    public void onEnable() {
        for (var entry : config.getFloatingtexts().entrySet()) {
            manager.spawnText(entry.getKey(), entry.getValue());
        }
        
        log.info("Loaded and spawned " + config.getFloatingtexts().size() + " floating texts.");

        Registries.COMMANDS.register(new FTCommand(config, manager));
        Server.getInstance().getEventBus().registerListener(new PlayerListener(manager));

        log.info("FloatingTextParticles Enabled!");
    }

    @Override
    public void onDisable() {
        manager.clearAll();
        config.save();
        log.info("FloatingTextParticles Disabled!");
    }
}

