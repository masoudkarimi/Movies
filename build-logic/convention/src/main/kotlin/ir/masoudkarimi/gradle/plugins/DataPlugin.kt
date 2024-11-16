package ir.masoudkarimi.gradle.plugins

import ir.masoudkarimi.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class DataPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply {
        apply("ir.masoudkarimi.gradle.android.library")
        apply("ir.masoudkarimi.gradle.android.hilt")
      }

      dependencies {
        add("implementation", project(":domain:model"))
      }
    }
  }
}