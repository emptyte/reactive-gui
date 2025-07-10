plugins {
  id("reactive-gui.runtime-bukkit-conventions")
}

dependencies {
  compileOnlyApi(project(":${rootProject.name}-runtime-bukkit-api"))

}
