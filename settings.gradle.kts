rootProject.name = "Movies"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
include(":app")
include(":feature:movies-list")
include(":feature:movie-detail")
include(":data:network")
include(":data:movies")
include(":data:basket")
include(":data:feature-flag")
include(":domain:model")
include(":domain:movies")
include(":domain:basket")
include(":domain:feature-flag")
include(":core:android")
include(":core:designsystem")
