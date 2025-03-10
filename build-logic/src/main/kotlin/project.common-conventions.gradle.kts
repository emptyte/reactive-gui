plugins {
  id("project.base-conventions")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

dependencies {
  implementation(libs.paper)
}
