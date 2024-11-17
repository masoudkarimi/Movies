plugins {
    id("ir.masoudkarimi.gradle.android.data")
}

android {
    namespace = "ir.masoudkarimi.movies"
}

dependencies {
    implementation(projects.domain.model)
    implementation(projects.data.network)
    implementation(projects.domain.movies)
}