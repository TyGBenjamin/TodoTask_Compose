plugins {
    id(Plugins.androidApp).version(Versions.androidGradle) apply false
    id(Plugins.androidLibrary).version(Versions.androidGradle) apply false
    id(Plugins.kotlinAndroid).version(Versions.kotlin) apply false
    id(Plugins.serializerPlug).version(Versions.kotlin) apply false
    id(Plugins.navigationPlug).version(Versions.navigation) apply false
    id(Plugins.daggerPlug).version(Versions.dagger) apply false
}

//apply(from = "android-config/android.config.gradle.kts")

