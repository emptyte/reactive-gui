plugins{
  id("reactive-gui.runtime-bukkit-conventions")
  id("io.papermc.paperweight.userdev")
}

dependencies {
  paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")

  compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
  compileOnly(project(":${rootProject.name}-runtime-bukkit-adapt"))
}

tasks {
  assemble {
    dependsOn(reobfJar)
  }
}
