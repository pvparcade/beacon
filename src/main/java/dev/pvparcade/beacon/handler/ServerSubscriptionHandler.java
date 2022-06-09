package dev.pvparcade.beacon.handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.pvparcade.beacon.BeaconPlugin;
import dev.pvparcade.beacon.server.impl.Server;
import dev.pvparcade.beacon.type.ServerMessageType;
import dev.pvparcade.pulse.subscriber.SubscriptionHandler;

public class ServerSubscriptionHandler implements SubscriptionHandler {
  private final JsonParser JSON_PARSER = new JsonParser();

  @Override
  public void handleMessage(String message) {
    JsonObject object = JSON_PARSER.parse(message).getAsJsonObject();

    ServerMessageType type = ServerMessageType.valueOf(object.get("type").getAsString());
    String id = object.get("id").getAsString();

    switch (type) {
      case SERVER_HEARTBEAT -> {
        Server server = BeaconPlugin.getPlugin().getServerManager().getServerMap().computeIfAbsent(id, Server::new);
        server.setCategory(object.get("category").getAsString());
        server.setDisplayName(object.get("displayName").getAsString());
        server.setOnlinePlayers(object.get("onlinePlayers").getAsInt());
        server.setMaxPlayers(object.get("maxPlayers").getAsInt());
        server.setWhitelisted(object.get("whitelisted").getAsBoolean());
        server.setLastHeartbeatTime(System.currentTimeMillis());
        break;
      }
      case SERVER_SHUTDOWN -> {
        BeaconPlugin.getPlugin().getServerManager().getServerMap().remove(id);
        break;
      }
    }
  }
}
