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

include(":core:common:domain")
include(":core:database")
include(":core:designsystem")
include(":core:network")

include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:ui")
include(":feature:profile:data")
include(":feature:profile:domain")
include(":feature:profile:ui")