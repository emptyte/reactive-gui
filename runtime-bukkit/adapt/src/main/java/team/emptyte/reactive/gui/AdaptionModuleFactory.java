package team.emptyte.reactive.gui;

import org.jetbrains.annotations.NotNull;
import team.emptyte.reactive.gui.minecraft.application.ServerVersion;

public final class AdaptionModuleFactory {
  private AdaptionModuleFactory() {
    throw new UnsupportedOperationException("This class cannot be instantiated");
  }

  public static @NotNull AdaptionModule create() {
    final String className = "team.emptyte.template.AdaptionModule" + ServerVersion.CURRENT;
    try {
      final Class<?> clazz = Class.forName(className);
      final Object module = clazz.getConstructor().newInstance();
      if (!(module instanceof AdaptionModule)) {
        throw new IllegalStateException("Invalid adaption module: '"
          + className + "'. It doesn't implement " + AdaptionModule.class);
      }
      return (AdaptionModule) module;
    } catch (final ClassNotFoundException e) {
      throw new IllegalStateException("Adaption module not found: '" + className + '.');
    } catch (final ReflectiveOperationException e) {
      throw new IllegalStateException("Failed to instantiate adaption module", e);
    }
  }
}
