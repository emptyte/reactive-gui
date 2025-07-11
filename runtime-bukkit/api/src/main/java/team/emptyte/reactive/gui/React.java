package team.emptyte.reactive.gui;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import team.emptyte.reactive.gui.component.Component;
import team.emptyte.reactive.gui.minecraft.server.ServerVersion;

public interface React {
  static @NotNull React createRoot() {
    final String className = "team.emptyte.reactive.gui.React" + ServerVersion.MINECRAFT_VERSION;
    try {
      final Class<?> clazz = Class.forName(className);
      final Object module = clazz.getConstructor().newInstance();
      if (!(module instanceof React)) {
        throw new IllegalStateException("Class '" + className + "' does not implement React interface.");
      }
      return (React) module;
    } catch (final ClassNotFoundException e) {
      throw new IllegalStateException("React adaption module not found: '"
        + className + "'. Make sure the correct version is included in the classpath.", e);
    } catch (final ReflectiveOperationException e) {
      throw new IllegalStateException("Failed to instantiate React adaption module: '"
        + className + "'. Ensure it has a public no-arg constructor.", e);
    }
  }

  void render(final @NotNull Player player, final @NotNull Component root);
}
