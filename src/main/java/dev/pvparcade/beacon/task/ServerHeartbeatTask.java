package dev.pvparcade.beacon.task;

import com.google.gson.JsonObject;
import dev.pvparcade.beacon.BeaconPlugin;
import dev.pvparcade.beacon.type.ServerMessageType;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class ServerHeartbeatTask extends BukkitRunnable {
  private final BeaconPlugin plugin;

  public void run() {
    JsonObject object = new JsonObject();
    object.addProperty("type", ServerMessageType.SERVER_HEARTBEAT.name());
    object.addProperty("id", this.plugin.getServerManager().getId());
    object.addProperty("displayName", this.plugin.getServerManager().getDisplayName());
    object.addProperty("category", this.plugin.getServerManager().getCategory());
    object.addProperty("onlinePlayers", Bukkit.getOnlinePlayers().size());
    object.addProperty("maxPlayers", Bukkit.getMaxPlayers());
    object.addProperty("whitelisted", Bukkit.hasWhitelist());

    this.plugin.getPublisher().publish(object.toString());
  }
}
