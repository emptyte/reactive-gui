plugins {
  id("java-platform")
  id("reactive-gui.base-conventions")
}

indra {
  configurePublications {
    from(components["javaPlatform"])
  }
}

dependencies {
  constraints {
    sequenceOf(
      "api",
      "test-plugin",
      "adapt-v1_21_7",
      "adapt-v1_21_6",
      "adapt-v1_21_5",
      "adapt-v1_21_4",
      "adapt-v1_21_3",
      "adapt-v1_21_1",
      "adapt-v1_21",
      "adapt-v1_20_6",
      "adapt-v1_20_4"
    ).forEach {
      api(project(":reactive-gui-runtime-bukkit-$it"))
    }
  }
}
