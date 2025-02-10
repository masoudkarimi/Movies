plugins {
    id("ir.masoudkarimi.gradle.android.data")
}

android {
    namespace = "ir.masoudkarimi.basket"
}

dependencies {
    implementation(projects.domain.model)
    implementation(projects.domain.basket)
    implementation(platform(libs.arrow.bom))
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
}