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
      "runtime-bukkit-api",
      "runtime-bukkit-adapt",
      "runtime-bukkit-adapt-v1_21_7",
      "runtime-bukkit-adapt-v1_21_6",
      "runtime-bukkit-adapt-v1_21_5",
      "runtime-bukkit-adapt-v1_21_4",
      "runtime-bukkit-adapt-v1_21_3",
      "runtime-bukkit-adapt-v1_21_1",
      "runtime-bukkit-adapt-v1_21",
      "runtime-bukkit-adapt-v1_20_6",
      "runtime-bukkit-adapt-v1_20_4"
    ).forEach {
      api(project(":reactive-gui-$it"))
    }
  }
}
