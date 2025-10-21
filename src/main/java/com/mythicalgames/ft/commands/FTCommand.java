package com.mythicalgames.ft.commands;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.permission.PermissionGroups;
import org.joml.Vector3f;

import com.mythicalgames.ft.FloatingTextManager;
import com.mythicalgames.ft.config.FloatingText;
import com.mythicalgames.ft.config.FloatingTextConfig;

public class FTCommand extends Command {

    private final FloatingTextConfig config;
    private final FloatingTextManager manager;

    public FTCommand(FloatingTextConfig config, FloatingTextManager manager) {
        super("ft", "Floating text command");
        getPermissions().forEach(PermissionGroups.OPERATOR::addPermission); 
        this.config = config;
        this.manager = manager;
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
            .key("create")
            .key("id")
            .msg("Floating Text")
            .exec(context -> {
                String id = context.getResult(1);
                String message = context.getResult(2);
                EntityPlayer player = context.getSender().asPlayer();

                if (player == null) {
                    context.addOutput("§l§7[§dFloatingTextParticles§7]§r§c You must be a player to run this command.");
                    return context.fail();
                }

                if (config.getFloatingtexts().containsKey(id)) {
                    context.addOutput("§l§7[§dFloatingTextParticles§7]§r§c FloatingText with that ID already exists.");
                    return context.fail();
                }

                Location3dc loc = player.getLocation();
                Vector3f pos = new Vector3f((float) loc.x(), (float) loc.y(), (float) loc.z());

                FloatingText ft = new FloatingText(player.getWorld().getName(), pos, message);

                config.getFloatingtexts().put(id, ft);
                config.save();

                manager.spawnText(id, ft);
                context.addOutput("§l§7[§dFloatingTextParticles§7]§r§a FloatingText '" + id + "' created at your location.");
                return context.success();
            })

            .root()
            .key("delete")
            .str("id")
            .exec(context -> {
                String id = context.getResult(1);

                if (!config.getFloatingtexts().containsKey(id)) {
                    context.addOutput("§l§7[§dFloatingTextParticles§7]§r§c No FloatingText with that ID.");
                    return context.fail();
                }

                config.getFloatingtexts().remove(id);
                config.save();

                manager.removeText(id);

                context.addOutput("§l§7[§dFloatingTextParticles§7]§r§a FloatingText '" + id + "' removed.");
                return context.success();
            });
    }
}
