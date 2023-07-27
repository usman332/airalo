// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false

}


buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = Config.ClassPaths.pluginGradle)
        jcenter() //here
        maven(url = "https://jitpack.io")

    }

    dependencies {
        classpath(Config.ClassPaths.androidGradle)
        classpath(Config.ClassPaths.kotlinGradle)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
        classpath(Config.ClassPaths.daggerHiltGradle)
//        classpath(Config.ClassPaths.navigationSafArgsGradle)
        // KtLint
        classpath(Config.ClassPaths.ktLint)
    }
}

/*
apply(from = "gradle/jacoco.gradle")
*/

/*allprojects {
*//*
    apply(plugin = Config.ClassPaths.pluginKtLint) // Version should be inherited from parent
*//*
    repositories {
        google()
        mavenCentral()
        maven(url = Config.ClassPaths.googleUrl)
        maven(url = Config.ClassPaths.jitPackUrl)
    }
}*/

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}