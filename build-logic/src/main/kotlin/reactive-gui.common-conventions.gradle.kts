import com.diffplug.gradle.spotless.FormatExtension
import java.util.*

plugins {
  id("reactive-gui.base-conventions")
  id("net.kyori.indra")
  id("net.kyori.indra.crossdoc")
  id("net.kyori.indra.licenser.spotless")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

indra {
  javaVersions {
    target(21)
    minimumToolchain(21)
    strictVersions(true)
  }
}

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies{
  api(platform(project(":reactive-gui-bom")))
  compileOnlyApi(libs.annotations)
}

spotless {
  fun FormatExtension.applyCommon() {
    trimTrailingWhitespace()
    endWithNewline()
    indentWithSpaces(2)
  }
  java {
    importOrderFile(rootProject.file(".spotless/emptyte.importorder"))
    removeUnusedImports()
    applyCommon()
  }
  kotlinGradle {
    applyCommon()
  }
}

indraCrossdoc {
  baseUrl().set(providers.gradleProperty("javadocPublishRoot"))
  nameBasedDocumentationUrlProvider {
    projectNamePrefix.set("metadata-")
  }
}

java {
  withJavadocJar()
}

tasks {
  generateOfflineLinks {
  }

  jar {
    manifest {
      attributes(
        "Specification-Version" to project.version,
        "Specification-Vendor" to "emptyte-team",
        "Implementation-Build-Date" to Date()
      )
    }
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name()
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name()
    dependsOn(spotlessApply)
    options.compilerArgs.add("-parameters")
  }
}
