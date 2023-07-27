// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Config.Plugins.androidApplication) version "8.0.2" apply false
    id(Config.Plugins.androidLibrary) version "8.0.2" apply false
    id(Config.Plugins.jetBrainkotlinAndroid) version "1.8.10" apply false
    id(Config.Plugins.daggerHilt) version "2.47" apply false

}


buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = Config.ClassPaths.pluginGradle)
        maven(url = Config.ClassPaths.jitPackUrl)

    }

    dependencies {
        classpath(Config.ClassPaths.androidGradle)
        classpath(Config.ClassPaths.kotlinGradle)
        classpath(Config.ClassPaths.daggerHiltGradle)
        // KtLint
        classpath(Config.ClassPaths.ktLint)
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}