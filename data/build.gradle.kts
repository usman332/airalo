import dependencies.RemoteDep
plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.jetBrainkotlinAndroid)

}

android {
    namespace = "com.usman.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
}

dependencies {
    // Modules
    implementation(project(Modules.common))
    implementation(project(Modules.domain))

    // Kotlin
    implementation(RemoteDep.kotlin)
    // JavaX
    implementation(RemoteDep.javax)
    // Network (Retrofit, OkHttp, Interceptor, Gson Converter)
    RemoteDep.retrofit.forEach { implementation(it) }
    // Coroutines
    implementation(RemoteDep.coroutineCore)

    // Test Dependencies
    testImplementation(RemoteDep.Test.junit)
    testImplementation(RemoteDep.Test.assertJ)
    testImplementation(RemoteDep.Test.mockitoKotlin)
    testImplementation(RemoteDep.Test.mockitoInline)
    testImplementation(RemoteDep.Test.coroutines)
}
