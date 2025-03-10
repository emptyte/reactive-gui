plugins {
  id("project.common-conventions")
}

dependencies {
  compileOnlyApi(project(":${rootProject.name}-api"))
  compileOnlyApi(project(":${rootProject.name}-infrastructure-configurate"))
}
