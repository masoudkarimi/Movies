plugins {
    id("ir.masoudkarimi.gradle.android.library")
}

android {
    namespace = "ir.masoudkarimi.core.android"
}

dependencies {
    implementation(libs.lifecycle.viewmodel.compose)
}