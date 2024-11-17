package ir.masoudkarimi.gradle.plugins

import ir.masoudkarimi.gradle.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * To use this plugin add this in your gradle file:
 * ```
 * plugins {
 *     id("ir.masoudkarimi.gradle.jvm.library")
 * }
 * ```
 */
class LibraryJvmPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}