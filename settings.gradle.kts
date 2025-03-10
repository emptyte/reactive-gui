pluginManagement {
  includeBuild("build-logic")
}

rootProject.name = "template"

sequenceOf(
  "plugin",
  "adapt",
  "api"
).forEach {
  include(":${rootProject.name}-$it")
  project(":${rootProject.name}-$it").projectDir = file(it)
}

sequenceOf(
  "configurate-yaml",
  "configurate"
).forEach {
  include(":${rootProject.name}-infrastructure-$it")
  project(":${rootProject.name}-infrastructure-$it").projectDir = file("infrastructure/$it")
}

sequenceOf(
  "1_20_2",
  "1_20_4",
  "1_20_6",
  "1_21",
  "1_21_1",
  "1_21_3",
  "1_21_4",
).forEach {
  include(":${rootProject.name}-adapt-v$it")
  project(":${rootProject.name}-adapt-v$it").projectDir = file("adapt/v$it")
}

