plugins{
  id("project.base-conventions")
  id("io.papermc.paperweight.userdev")
}

dependencies {
  compileOnly(project(":${rootProject.name}-adapt"))
}

tasks {
  assemble {
    dependsOn(reobfJar)
  }
}
