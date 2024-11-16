package ir.masoudkarimi.gradle.plugins

import com.android.build.gradle.LibraryExtension
import ir.masoudkarimi.gradle.configureAndroidDefaultConfigs
import ir.masoudkarimi.gradle.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidDefaultConfigs()
                defaultConfig.targetSdk = libs.findVersion("targetSdk").get().toString().toInt()

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            dependencies {
                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findBundle("unitTest").get())
            }
        }
    }
}