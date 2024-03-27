package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinLooseWrapper implements Listener {

    private final BJSG plugin;
    public PlayerJoinLooseWrapper(final BJSG plugin) {
        this.plugin = plugin;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.createLooseWrapper(event.getPlayer().getUniqueId());
    }
}
