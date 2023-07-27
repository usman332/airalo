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
        /*  maven(url = Config.ClassPaths.googleUrl)
          maven(url = Config.ClassPaths.jitPackUrl)*/
    }
}

rootProject.name = "Airalo"
include (":app")
include (":data")
include ("domain")
include (":common")

/*
include(":temp")
*/
