pluginManagement {
  includeBuild("build-logic")
}

rootProject.name = "reactive-gui"

sequenceOf(
  "api",
  "bom"
).forEach {
  include(":${rootProject.name}-$it")
  project(":${rootProject.name}-$it").projectDir = file(it)
}

sequenceOf(
  "adapt",
  "api"
).forEach {
  include(":${rootProject.name}-runtime-bukkit-$it")
  project(":${rootProject.name}-runtime-bukkit-$it").projectDir = file("runtime-bukkit/$it")
}

sequenceOf(
  "1_20_4",
  "1_20_6",
  "1_21",
  "1_21_1",
  "1_21_3",
  "1_21_4",
  "1_21_5",
  "1_21_6",
  "1_21_7"
).forEach {
  include(":${rootProject.name}-runtime-bukkit-adapt-v$it")
  project(":${rootProject.name}-runtime-bukkit-adapt-v$it").projectDir = file("runtime-bukkit/adapt/v$it")
}

