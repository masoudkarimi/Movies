plugins {
    id("ir.masoudkarimi.gradle.android.data")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ir.masoudkarimi.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.okhttp.logginginterceptor)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)

    implementation(projects.domain.model)
}