package team.emptyte.reactive.gui.minecraft.server;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * Represents the version of the Minecraft server.
 */
public record ServerVersion(int major, int minor, int patch) {
  public static final String MINECRAFT_VERSION = Bukkit.getMinecraftVersion();
  public static final ServerVersion CURRENT = ServerVersion.from(MINECRAFT_VERSION);

  public static @NotNull ServerVersion from(final @NotNull String version) {
    final String[] split = version.split(Pattern.quote("."));
    if (split.length < 2 || split.length > 3) {
      throw new IllegalArgumentException("Invalid version format: " + version);
    }
    return new ServerVersion(
      Integer.parseInt(split[0]),
      Integer.parseInt(split[1]),
      split.length == 3 ? Integer.parseInt(split[2]) : -1
    );
  }

  @Override
  public @NotNull String toString() {
    return this.patch >= 0 ?
      String.format("%d_%d_%d", this.major, this.minor, this.patch) :
      String.format("%d_%d", this.major, this.minor);
  }
}
