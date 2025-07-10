plugins {
  id("reactive-gui.runtime-bukkit-conventions")
}

dependencies {
  compileOnlyApi(libs.paper.api)

  api(project(":${rootProject.name}-api"))
}
