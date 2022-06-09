package dev.pvparcade.beacon.api;

import dev.pvparcade.beacon.BeaconPlugin;
import dev.pvparcade.beacon.server.impl.Server;

import java.util.ArrayList;
import java.util.List;

public class Beacon {

    public static Server getServerFromId(String id) {
        return BeaconPlugin.getPlugin().getServerManager().getServerMap().get(id);
    }

    public static List<Server> getServersFromCategory(String category) {
        List<Server> servers = new ArrayList<>();

        for (Server server : BeaconPlugin.getPlugin().getServerManager().getServerMap().values()) {
            if (server.getCategory().equalsIgnoreCase(category))
                servers.add(server);
        }

        return servers;
    }

    public static Server getLeastPopulatedServerFromCategory(String category) {
        List<Server> servers = getServersFromCategory(category);

        Server bestServer = null;
        int bestPlayerCount = 9999;

        for (Server server : servers) {
            if (server.getOnlinePlayers() < bestPlayerCount) {
                bestServer = server;
                bestPlayerCount = server.getOnlinePlayers();
            }
        }

        return bestServer;
    }
}
