import dependencies.UiDep
import dependencies.RemoteDep

plugins {
    id(Config.Plugins.androidApplication)
    id(Config.Plugins.kotlinAndroid)
    // id(Config.Plugins.navigationSafArgs)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.daggerHilt)
    id("org.jetbrains.kotlin.android")
}

configurations.all {
    resolutionStrategy {
        // Use version 2.5.1 for lifecycle-viewmodel
        force("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
    }
}
android {
    compileSdkVersion(Config.Android.androidCompileSdkVersion)
    buildToolsVersion(Config.Android.androidBuildToolsVersion)
    namespace = Environments.Release.appId

    defaultConfig {
        applicationId = Environments.Release.appId
        minSdk = Config.Android.androidMinSdkVersion
        targetSdk = Config.Android.androidTargetSdkVersion
        versionCode = Environments.Release.appVersionCode
        versionName = Environments.Release.appVersionName

        testInstrumentationRunner = Config.testRunner
        multiDexEnabled = true

        // Configs
        buildConfigField("String", "BASE_URL", "\"" + Environments.Release.baseUrl + "\"")
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true

    }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //
    val SDP = "1.0.5"

    // Modules
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.common))

    //Multi Dex
    implementation(UiDep.multiDex)

    // Core Dependencies
    implementation(UiDep.kotlin)
    implementation(UiDep.coreKtx)
    implementation(UiDep.appCompat)
    implementation(UiDep.material)
    implementation(UiDep.constraint)
    implementation(UiDep.navigationFragmentKtx)
    implementation(UiDep.navigationUiKtx)
    implementation(UiDep.activityKtx)

    /*SDP & SSP dependencies powered by intuit*/
    implementation(UiDep.sdp)
    implementation(UiDep.ssp)

    // LifeCycle
    UiDep.LifeCycle.forEach {
        implementation(it)
    }

    // Network (Retrofit, OkHttp, Interceptor, Gson Converter)
    RemoteDep.retrofit.forEach { implementation(it) }

    // Dagger-Hilt
    UiDep.DaggerHilt.forEach {
        implementation(it)
    }
    UiDep.DaggerHiltKapt.forEach {
        kapt(it)
    }
    // Coroutines
    UiDep.Coroutines.forEach {
        implementation(it)
    }
    // Glide
    implementation(UiDep.glide)
    kapt(UiDep.glideKapt)
    // Timber
    implementation(UiDep.timber)

    // Lottie animation
    implementation(UiDep.lottie)

   /* implementation(UiDep.mkLoader)*/

    // Test Dependencies
    testImplementation(UiDep.Test.junit)
    testImplementation(UiDep.Test.assertJ)
    testImplementation(UiDep.Test.mockitoKotlin)
    testImplementation(UiDep.Test.mockitoInline)
    testImplementation(UiDep.Test.coroutines)
    testImplementation(UiDep.Test.androidxArchCore)
    testImplementation(UiDep.Test.robolectric)
    testImplementation(UiDep.Test.testExtJunit)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}