package com.usman.airalo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

/**
 * The custom application class for the Android application.
 *
 * This class is annotated with [@HiltAndroidApp](https://dagger.dev/hilt/components) to enable Hilt dependency injection
 * and facilitate the creation of application-wide components.
 *
 * The application extends [Application] to represent the global state of the application.
 */
@HiltAndroidApp
class App : Application() {

    /**
     * Called when the application is attached to the context. This is the first callback received in the application lifecycle.
     *
     * This method installs the [MultiDex] support for the application, allowing it to have more than 65536 methods (64K limit).
     * It is necessary for applications that use a large number of methods, such as when using multiple libraries or complex code.
     *
     * @param base The [Context] to which the application is attached.
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
