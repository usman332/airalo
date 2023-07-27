import dependencies.RemoteDep

plugins {
    id(Config.Plugins.kotlin)
    id(Config.Plugins.javaLibrary)
}
java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}

dependencies {
    implementation(RemoteDep.retrofitGsonConverter)

}