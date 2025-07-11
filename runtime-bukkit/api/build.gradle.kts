plugins {
  id("reactive-gui.runtime-bukkit-conventions")
}

dependencies {
  compileOnly(libs.paper.api)

  api(project(":${rootProject.name}-api"))
}
