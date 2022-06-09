package dev.pvparcade.beacon.server;

import com.google.gson.JsonObject;
import dev.pvparcade.beacon.BeaconPlugin;
import dev.pvparcade.beacon.server.impl.Server;
import dev.pvparcade.beacon.type.ServerMessageType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ServerManager {
  private final Map<String, Server> serverMap = new HashMap<>();

  private String id;
  private String displayName;
  private String category;

  public ServerManager(BeaconPlugin plugin) {
    this.id = plugin.getConfiguration().getString("server.id");
    this.displayName = plugin.getConfiguration().getString("server.display-name");
    this.category = plugin.getConfiguration().getString("server.category");
  }

  public void sendServerShutdownMessage() {
    JsonObject object = new JsonObject();
    object.addProperty("type", ServerMessageType.SERVER_SHUTDOWN.name());
    object.addProperty("id", this.id);

    BeaconPlugin.getPlugin().getPublisher().publish(object.toString());
  }
}
