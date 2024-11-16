package ir.masoudkarimi.gradle.plugins

import com.android.build.api.dsl.ApplicationExtension
import ir.masoudkarimi.gradle.configureAndroidCompose
import ir.masoudkarimi.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ApplicationComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)

            dependencies {
                add("implementation", libs.findLibrary("androidx.activity.compose").get())
            }
        }
    }
}