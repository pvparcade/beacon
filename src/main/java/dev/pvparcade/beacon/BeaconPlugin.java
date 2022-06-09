package dev.pvparcade.beacon;

import dev.pvparcade.beacon.handler.ServerSubscriptionHandler;
import dev.pvparcade.beacon.server.ServerManager;
import dev.pvparcade.beacon.task.ServerCleanupTask;
import dev.pvparcade.beacon.task.ServerHeartbeatTask;
import dev.pvparcade.pulse.Pulse;
import dev.pvparcade.pulse.publisher.Publisher;
import dev.pvparcade.pulse.subscriber.Subscriber;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

@Getter
public class BeaconPlugin extends JavaPlugin {
  @Getter private static BeaconPlugin plugin;

  private FileConfiguration configuration;
  private ServerManager serverManager;

  private Pulse pulse;
  private Publisher publisher;
  private Subscriber subscriber;

  private BukkitRunnable heartbeatTask;
  private BukkitRunnable cleanupTask;

  @Override
  public void onEnable() {
    this.plugin = this;

    this.configuration = getConfigAndCreate();
    this.serverManager = new ServerManager(this.plugin);

    this.pulse = new Pulse(this.configuration.getString("redis.host"), this.configuration.getInt("redis.port"),
            this.configuration.getString("redis.password"), this.configuration.getBoolean("redis.authenticate"));
    this.publisher = new Publisher(this.pulse, "beacon");
    this.subscriber = new Subscriber(this.pulse, "beacon", new ServerSubscriptionHandler());

    this.heartbeatTask = new ServerHeartbeatTask(this.plugin);
    this.heartbeatTask.runTaskTimerAsynchronously(this.plugin, 20L, 20L);

    this.cleanupTask = new ServerCleanupTask(this.plugin);
    this.cleanupTask.runTaskTimerAsynchronously(this.plugin, 20L, 20L);
  }

  @Override
  public void onDisable() {
    this.serverManager.sendServerShutdownMessage();
    this.pulse.close();

    this.plugin = null;
  }

  private FileConfiguration getConfigAndCreate() {
    File dataFolder = this.plugin.getDataFolder();
    if (!dataFolder.exists()) {
      this.plugin.saveDefaultConfig();
    }

    return this.getConfig();
  }
}
