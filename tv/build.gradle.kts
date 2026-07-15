plugins {
    id("ir.masoudkarimi.gradle.android.application")
}

android {
    namespace = "ir.masoudkarimi.movies.tv"
    defaultConfig {
        applicationId = "ir.masoudkarimi.movies.tv"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.feature.moviesList)
    implementation(projects.feature.movieDetail)

    implementation(projects.data.network)
    implementation(projects.data.movies)
    implementation(projects.data.basket)
    implementation(projects.data.featureFlag)

    implementation(projects.domain.model)
    implementation(projects.domain.movies)
    implementation(projects.domain.basket)
    implementation(projects.domain.featureFlag)

    implementation(libs.androidx.tv.material)
    implementation(libs.coil)
    implementation(libs.coil.network)

    implementation(libs.hilt.navigation.compose)
}