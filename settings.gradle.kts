pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "GithubClient"
include(":app")
include(":core:data")
include(":core:database")
include(":core:testing")
include(":feature:search")
include(":feature:setting")
include(":feature:favorite")

