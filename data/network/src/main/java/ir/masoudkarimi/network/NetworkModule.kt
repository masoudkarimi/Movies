package ir.masoudkarimi.network

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    @Named("CacheInterceptor")
    abstract fun bindCacheInterceptor(cacheControl: CacheInterceptor): Interceptor

    companion object {

        @Provides
        @Singleton
        fun providesJson(): Json = Json {
            ignoreUnknownKeys = true
        }

        @Provides
        @Reusable
        fun provideLogginInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides
        @Singleton
        fun provideOkHttp(
            loggingInterceptor: HttpLoggingInterceptor,
            @Named("CacheInterceptor")
            cacheInterceptor: Interceptor,
            @ApplicationContext
            applicationContext: Context
        ): OkHttpClient {
            return OkHttpClient
                .Builder()
                .cache(Cache(File(applicationContext.cacheDir, "http-cache"), 10L * 1024 * 1024))
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://moviesapi.ir/api/v1/")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .client(okHttpClient)
                .build()
        }

        @Provides
        @Singleton
        fun provideMoviesService(retrofit: Retrofit): MoviesService {
            return retrofit.create(MoviesService::class.java)
        }
    }
}
