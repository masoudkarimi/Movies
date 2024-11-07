package ir.masoudkarimi.network

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.masoudkarimi.network.resource.MoviesRemoteResource
import ir.masoudkarimi.network.resource.MoviesRemoteSourceImpl
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindMoviesRemoteSource(impl: MoviesRemoteSourceImpl): MoviesRemoteResource

    companion object {

        @Provides
        @Singleton
        fun providesJson(): Json = Json {
            ignoreUnknownKeys = true
        }

        @Provides
        @Singleton
        fun provideOkHttp(): OkHttpClient {
            return OkHttpClient.Builder().build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://moviesapi.ir/api/v1/")
                .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
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
