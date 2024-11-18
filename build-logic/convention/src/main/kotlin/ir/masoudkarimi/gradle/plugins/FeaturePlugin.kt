package ir.masoudkarimi.gradle.plugins

import ir.masoudkarimi.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * To use this plugin add this in your gradle file:
 * ```
 * plugins {
 *     id("ir.masoudkarimi.gradle.android.feature")
 * }
 * ```
 */
class FeaturePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("ir.masoudkarimi.gradle.android.library")
                apply("ir.masoudkarimi.gradle.android.hilt")
            }

            dependencies {
                add("implementation", project(":domain:model"))
                add("implementation", project(":core:android"))
                add("implementation", libs.findLibrary("hilt.navigation.compose").get())
            }
        }
    }
}