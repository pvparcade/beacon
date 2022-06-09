# Beacon
A plugin to manage Bukkit server instances

## How to use Beacon as a dependency?
Add this to your Gradle build config:
```
repositories {
    maven("https://repo.blueoxygen.net/releases")
}

dependencies {
    implementation("dev.pvparcade:beacon:1.0")
}
```

or in your Maven build config:

```
<repositories>
    <repository>
      <id>blueoxygen-releases</id>
      <name>BlueOxygen Repository</name>
      <url>https://repo.blueoxygen.net/releases</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>dev.pvparcade</groupId>
        <artifactId>beacon</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```