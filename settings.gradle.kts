pluginManagement {
    repositories {
        google()
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
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Pokfinder"

include(":app")

include(":core:database")
include(":core:designsystem")
include(":core:network")

include(":feature:home:data")
include(":feature:profile:data")

include(":domain")
include(":presentation")
