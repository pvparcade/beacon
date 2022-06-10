package dev.pvparcade.beacon.api;

import dev.pvparcade.beacon.BeaconPlugin;
import dev.pvparcade.beacon.server.impl.Server;

import java.util.ArrayList;
import java.util.List;

public class Beacon {

    /**
     * Returns a list of every server that is currently running.
     *
     * @return a list of servers
     */
    public static List<Server> getAllServers() {
        return BeaconPlugin.getPlugin().getServerManager().getServerMap().values().stream().toList();
    }

    /**
     * Returns a specific server by it's id.
     *
     * @return a server
     */
    public static Server getServerFromId(String id) {
        return BeaconPlugin.getPlugin().getServerManager().getServerMap().get(id);
    }

    /**
     * Returns a list of servers that are in a specific category.
     *
     * @param category the category of servers you want to get
     * @return a list of servers
     */
    public static List<Server> getServersFromCategory(String category) {
        List<Server> servers = new ArrayList<>();

        for (Server server : BeaconPlugin.getPlugin().getServerManager().getServerMap().values()) {
            if (server.getCategory().equalsIgnoreCase(category))
                servers.add(server);
        }

        return servers;
    }

    /**
     * Returns the least populated server by player count in a specific category.
     *
     * @param category the category of servers you want to get
     * @return a server
     */
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
