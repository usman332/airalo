package com.usman.airalo.di.core.module

import com.usman.data.util.DiskExecutor
import com.usman.data.util.DispatchersProvider
import com.usman.data.util.DispatchersProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/*
* Dagger module responsible for providing dependencies related to the application.
* This module is installed in the [SingletonComponent] which ensures that the provided
* dependencies are available throughout the entire application's lifecycle.
*/
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Provides an instance of [DiskExecutor] to handle disk-bound operations in the application.
     *
     * @return An instance of [DiskExecutor] used for disk I/O operations.
     */
    @Provides
    fun provideDiskExecutor(): DiskExecutor {
        return DiskExecutor()
    }

    /**
     * Provides an instance of [DispatchersProvider] to manage coroutines' dispatchers in the application.
     *
     * @return An instance of [DispatchersProvider] used for managing coroutines' dispatchers.
     */
    @Provides
    fun provideDispatchersProvider(): DispatchersProvider {
        return DispatchersProviderImpl
    }

}