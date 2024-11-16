package ir.masoudkarimi.gradle.plugins

import com.android.build.api.dsl.ApplicationExtension
import ir.masoudkarimi.gradle.configureAndroidDefaultConfigs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("ir.masoudkarimi.gradle.android.application.compose")
                apply("ir.masoudkarimi.gradle.android.hilt")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = "ir.masoudkarimi.movies"
                }
                configureAndroidDefaultConfigs()
                signingConfigs {
                    getByName("debug") {

                    }

                    create("release") {

                    }
                }

                buildTypes {
                    debug {
                        isMinifyEnabled = false
                        signingConfig = signingConfigs.findByName("debug")
                    }

                    release {
                        isMinifyEnabled = true
                        isShrinkResources = false
                        signingConfig = signingConfigs.findByName("debug")
                        proguardFiles("proguard-rules.pro")
                    }
                }

                sourceSets {
                    getByName("main") {
                        java {
                            srcDir("src/main/java")
                        }
                    }
                    getByName("debug") {
                        java {
                            srcDir("src/debug/java")
                        }
                    }
                    getByName("release") {
                        java {
                            srcDir("src/release/java")
                        }
                    }
                }
            }
        }
    }
}