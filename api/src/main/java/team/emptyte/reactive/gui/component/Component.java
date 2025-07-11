package team.emptyte.reactive.gui.component;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

public abstract class Component {
  private Component parent; // Nullable parent to allow root components without a parent

  private final Map<String, Object> states = new Object2ObjectOpenHashMap<>();

  protected Component() {
    this.parent = null;
  }

  public @Nullable Component parent() {
    return this.parent;
  }

  public void parent(final @NotNull Component parent) {
    this.parent = parent;
  }

  @SuppressWarnings("unchecked")
  public <S> @Nullable S getState(final @NotNull String key) {
    return (S) this.states.get(key);
  }

  public <S> void setState(final @NotNull String key, final @NotNull S value) {
    this.states.put(key, value);
  }

  public abstract @NotNull Collection<@NotNull Object> render();
}
