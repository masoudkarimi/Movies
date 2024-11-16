plugins {
    id("ir.masoudkarimi.gradle.android.application")
}

android {
    namespace = "ir.masoudkarimi.movies"
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(projects.domain.featureFlag)
}