plugins {
    `kotlin-dsl`
}

group = "ir.masoudkarimi.gradle"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.androidGradlePlugin)
    compileOnly(libs.kotlinGradlePlugin)
    compileOnly(libs.kspGradlePlugin)
    compileOnly(libs.kotlinComposeCompilerPlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidLibraryCompose") {
            id = "ir.masoudkarimi.gradle.android.library.compose"
            implementationClass = "ir.masoudkarimi.gradle.plugins.LibraryComposePlugin"
        }
        register("androidApplicationCompose") {
            id = "ir.masoudkarimi.gradle.android.application.compose"
            implementationClass = "ir.masoudkarimi.gradle.plugins.ApplicationComposePlugin"
        }
        register("androidApplication") {
            id = "ir.masoudkarimi.gradle.android.application"
            implementationClass = "ir.masoudkarimi.gradle.plugins.ApplicationPlugin"
        }
        register("androidLibrary") {
            id = "ir.masoudkarimi.gradle.android.library"
            implementationClass = "ir.masoudkarimi.gradle.plugins.AndroidLibraryPlugin"
        }
        register("androidFeature") {
            id = "ir.masoudkarimi.gradle.android.feature"
            implementationClass = "ir.masoudkarimi.gradle.plugins.FeaturePlugin"
        }
        register("androidHilt") {
            id = "ir.masoudkarimi.gradle.android.hilt"
            implementationClass = "ir.masoudkarimi.gradle.plugins.HiltPlugin"
        }
        register("androidDomain") {
            id = "ir.masoudkarimi.gradle.android.domain"
            implementationClass = "ir.masoudkarimi.gradle.plugins.DomainPlugin"
        }
        register("androidData") {
            id = "ir.masoudkarimi.gradle.android.data"
            implementationClass = "ir.masoudkarimi.gradle.plugins.DataPlugin"
        }
        register("jvmLibrary") {
            id = "ir.masoudkarimi.gradle.jvm.library"
            implementationClass = "ir.masoudkarimi.gradle.plugins.LibraryJvmPlugin"
        }
    }
}