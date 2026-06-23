pluginManagement {
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

rootProject.name = "InterViewPrepApp"
include(":app")
include(":core-domain")
include(":core-data")
include(":feature-login")
include(":feature-booking")
include(":core-common")
include(":core_ui")
include(":feature-home")
include(":feature-player")
