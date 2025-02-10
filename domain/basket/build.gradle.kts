plugins {
    id("ir.masoudkarimi.gradle.android.domain")
}

dependencies {
    implementation(platform(libs.arrow.bom))
    implementation(libs.arrow.core)
}
