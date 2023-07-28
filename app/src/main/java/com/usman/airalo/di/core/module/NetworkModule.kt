package com.usman.airalo.di.core.module
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.usman.airalo.BuildConfig
import com.usman.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger module responsible for providing network-related dependencies in the application.
 *
 * This module is installed in the [SingletonComponent], ensuring that the provided
 * dependencies are available throughout the entire application's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /**
     * Provides an instance of [Retrofit] for making network requests.
     *
     * @param okHttpClient The [OkHttpClient] used to build the [Retrofit] instance, implementing [OkHttpClient].
     * @param gsonConverterFactory The [GsonConverterFactory] for parsing JSON responses, implementing [GsonConverterFactory].
     * @return An instance of [Retrofit] to handle network requests and responses.
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            // .addCallAdapterFactory(CoroutineCallAdapterFactory()) // Uncomment this line if using CoroutineCallAdapterFactory
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    /**
     * Provides an instance of [OkHttpClient] to customize and manage HTTP requests.
     *
     * @return An instance of [OkHttpClient] used for managing HTTP requests and responses.
     */
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("X-localization", "en")
                    .build()
                val response = chain.proceed(request)
                response
            })
            .addInterceptor(logging)
            .build()
    }

    /**
     * Provides an instance of [GsonConverterFactory] to convert JSON responses into objects.
     *
     * @return An instance of [GsonConverterFactory] used for converting JSON to objects.
     */
    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    /**
     * Provides an instance of [ApiService] using the [Retrofit] instance.
     *
     * @param retrofit The [Retrofit] instance, implementing [Retrofit].
     * @return An instance of [ApiService] for defining API endpoints and making network requests.
     */
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
