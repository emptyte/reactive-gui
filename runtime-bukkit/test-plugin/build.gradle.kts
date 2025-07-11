import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import java.nio.file.Files
import java.util.*

plugins {
  id("reactive-gui.runtime-bukkit-conventions")
  id("com.github.johnrengelman.shadow") version "8.1.1"
  id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

dependencies {
  compileOnly(libs.paper.api)

  sequenceOf(
    "api",
    "adapt-v1_20_4",
    "adapt-v1_20_6",
    "adapt-v1_21",
    "adapt-v1_21_1",
    "adapt-v1_21_3",
    "adapt-v1_21_4",
    "adapt-v1_21_5",
    "adapt-v1_21_6",
    "adapt-v1_21_7"
  ).forEach {
    implementation(project(":${rootProject.name}-runtime-bukkit-$it"))
  }
}

bukkit {
  val pluginName = "EmptyteReactiveGui"

  name = pluginName
  version = project.version.toString()
  description = "This plugin provides metadata functionality for the Emptyte Reactive Gui project."
  website = "https://emptyte.team"
  author = "srvenient"

  main = "team.emptyte.reactive.gui.${pluginName}Plugin"

  // Generate paper-libraries.json from `library` and `paperLibrary` in `dependencies`
  generateLibrariesJson = true

  foliaSupported = true

  apiVersion = "1.20"

  load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
  prefix = "reactive-gui"
  defaultPermission = BukkitPluginDescription.Permission.Default.OP // TRUE, FALSE, OP or NOT_OP
}

tasks {
  shadowJar {
    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
  }
}
