package ir.masoudkarimi.gradle.plugins

import ir.masoudkarimi.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

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

                add("implementation", libs.findLibrary("androidx.ui").get())
                add("implementation", libs.findLibrary("androidx.material3").get())
                add("implementation", libs.findLibrary("hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("lifecycle.viewmodel.compose").get())
                add("implementation", libs.findLibrary("hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("lifecycle.runtime.compose").get())
                add("implementation", libs.findLibrary("lifecycle.viewmodel.compose").get())
                add("testImplementation", libs.findBundle("unitTest").get())
                add("androidTestImplementation", libs.findLibrary("androidx.junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.espresso.core").get())
            }
        }
    }
}