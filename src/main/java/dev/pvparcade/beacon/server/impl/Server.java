package dev.pvparcade.beacon.server.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Server {
  private final String id;
  private String displayName;
  private String category;
  private int onlinePlayers;
  private int maxPlayers;
  private boolean whitelisted;
  private long lastHeartbeatTime;
}
