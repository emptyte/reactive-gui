plugins {
  id("net.kyori.indra.publishing")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

indra {
  javaVersions {
    target(21)
    minimumToolchain(21)
    strictVersions(true)
  }
  checkstyle(libs.versions.checkstyle.get())

  github("emptyte-team", "reactive-gui") {
    ci(true)
  }
  mitLicense()

  configurePublications {
    pom {
      developers {
        developer {
          id.set("srvenient")
          name.set("Nelson Rodriguez Roa")
          url.set("https://github.com/srvenient")
          email.set("srvenient@gmail.com")
        }
      }
    }
  }
}
