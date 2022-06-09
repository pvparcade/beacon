package dev.pvparcade.beacon.task;

import dev.pvparcade.beacon.BeaconPlugin;
import dev.pvparcade.beacon.server.impl.Server;
import lombok.AllArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

@AllArgsConstructor
public class ServerCleanupTask extends BukkitRunnable {
  private final BeaconPlugin plugin;

  @Override
  public void run() {
    Iterator<String> it = this.plugin.getServerManager().getServerMap().keySet().iterator();

    while (it.hasNext()) {
      String id = it.next();
      Server server = this.plugin.getServerManager().getServerMap().get(id);

      if (server != null) {
        long now = System.currentTimeMillis();

        if (now - server.getLastHeartbeatTime() >= 15_000L) {
          it.remove();
        }
      }
    }
  }
}
