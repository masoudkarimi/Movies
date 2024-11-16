plugins {
    id("ir.masoudkarimi.gradle.android.data")
}

android {
    namespace = "ir.masoudkarimi.feature_flag"
}

dependencies {
    implementation(projects.domain.featureFlag)
}