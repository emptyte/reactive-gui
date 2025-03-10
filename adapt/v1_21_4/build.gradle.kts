plugins {
  id("project.adapter-conventions")
}

dependencies {
  paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")

  compileOnly(project(":${rootProject.name}-adapt"))
}
