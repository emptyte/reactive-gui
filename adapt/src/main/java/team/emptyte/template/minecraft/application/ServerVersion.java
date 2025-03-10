package team.emptyte.template.minecraft.application;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public record ServerVersion(int major, int minor, int patch) {
  public static final String MINECRAFT_VERSION = Bukkit.getMinecraftVersion();
  public static final ServerVersion CURRENT;

  static {
    final String[] split = MINECRAFT_VERSION.split(Pattern.quote("."));
    if (split.length != 2 && split.length != 3) {
      throw new IllegalStateException("Failed to determine minecraft version!");
    }
    final int major = Integer.parseInt(split[0]);
    final int minor = Integer.parseInt(split[1]);
    final int patch = split.length == 3 ? Integer.parseInt(split[2]) : -1;
    CURRENT = new ServerVersion(major, minor, patch);
  }

  public boolean isGreaterThan(final @NotNull ServerVersion other) {
    if (this.major > other.major) {
      return true;
    } else if (this.major == other.major) {
      if (this.minor > other.minor) {
        return true;
      } else if (this.minor == other.minor) {
        return this.patch > other.patch;
      }
    }
    return false;
  }

  public boolean isLessThan(final @NotNull ServerVersion other) {
    if (this.major < other.major) {
      return true;
    } else if (this.major == other.major) {
      if (this.minor < other.minor) {
        return true;
      } else if (this.minor == other.minor) {
        return this.patch < other.patch;
      }
    }
    return false;
  }

  public boolean isEqualTo(final @NotNull ServerVersion other) {
    return this.major == other.major && this.minor == other.minor && this.patch == other.patch;
  }

  public boolean isGreaterThanOrEqualTo(final @NotNull ServerVersion other) {
    return this.isGreaterThan(other) || this.isEqualTo(other);
  }

  public boolean isLessThanOrEqualTo(final @NotNull ServerVersion other) {
    return this.isLessThan(other) || this.isEqualTo(other);
  }

  @Override
  public @NotNull String toString() {
    if (this.patch == -1) {
      return this.major + "_" + this.minor;
    }
    return this.major + "_" + this.minor + "_" + this.patch;
  }
}
