pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter() //here
        maven(url = "https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() //here
        maven(url = "https://jitpack.io")

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
