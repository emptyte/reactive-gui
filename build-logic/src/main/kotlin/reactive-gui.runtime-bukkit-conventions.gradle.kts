plugins {
  id("reactive-gui.common-conventions")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

repositories {
  maven("https://repo.papermc.io/repository/maven-public/")
}
