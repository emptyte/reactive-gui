import com.diffplug.gradle.spotless.FormatExtension
import gradle.kotlin.dsl.accessors._2a260239b78d40794f556d7b1dabbe43.*
import gradle.kotlin.dsl.accessors._2a260239b78d40794f556d7b1dabbe43.checkstyle
import gradle.kotlin.dsl.accessors._2a260239b78d40794f556d7b1dabbe43.indra
import gradle.kotlin.dsl.accessors._2a260239b78d40794f556d7b1dabbe43.indraCrossdoc
import gradle.kotlin.dsl.accessors._2a260239b78d40794f556d7b1dabbe43.java
import gradle.kotlin.dsl.accessors._2a260239b78d40794f556d7b1dabbe43.spotless
import org.gradle.kotlin.dsl.*
import java.util.*

plugins {
  id("net.kyori.indra")
  id("net.kyori.indra.crossdoc")
  id("net.kyori.indra.checkstyle")
  id("net.kyori.indra.licenser.spotless")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

indra {
  javaVersions {
    target(21)
    minimumToolchain(21)
    strictVersions(true)
  }
  checkstyle(libs.versions.checkstyle.get())
}

repositories {
  mavenLocal()
  mavenCentral()
  maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
  checkstyle(libs.stylecheck)
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
    projectNamePrefix.set("template-")
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
    dependsOn(checkstyleMain)
    options.compilerArgs.add("-parameters")
  }
}
