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

rootProject.name = "delivery-zalyaeva-shift-2025"
include(":app")
include(":component:theme")
include(":component:components")
include(":shared:calculation")
include(":util:list")
include(":feature:history")
include(":feature:profile")
include(":feature:findPackage")
include(":feature:calculateDelivery")
include(":feature:calculation")