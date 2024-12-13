plugins {
    id("ir.masoudkarimi.gradle.android.feature")
    id("ir.masoudkarimi.gradle.android.library.compose")
}

android {
    namespace = "ir.masoudkarimi.movie_detail"
}

dependencies {
    implementation(projects.core.android)
    implementation(projects.domain.movies)
    implementation(projects.domain.basket)
    implementation(projects.domain.model)
    implementation(projects.core.designsystem)
    implementation(libs.coil)
    implementation(libs.coil.network)
}