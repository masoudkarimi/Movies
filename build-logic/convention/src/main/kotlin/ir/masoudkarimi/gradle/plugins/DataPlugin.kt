package ir.masoudkarimi.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

/**
 * To use this plugin add this in your gradle file:
 * ```
 * plugins {
 *     id("ir.masoudkarimi.gradle.android.data")
 * }
 * ```
 */
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