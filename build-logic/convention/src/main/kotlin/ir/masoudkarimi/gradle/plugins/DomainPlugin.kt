package ir.masoudkarimi.gradle.plugins

import ir.masoudkarimi.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class DomainPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply {
        apply("ir.masoudkarimi.gradle.jvm.library")
      }

      dependencies {
        add("implementation", project(":domain:model"))
        add("implementation", libs.findLibrary("kotlinx.coroutines.core").get())
        add("implementation", libs.findLibrary("javax.inject").get())

      }
    }
  }
}