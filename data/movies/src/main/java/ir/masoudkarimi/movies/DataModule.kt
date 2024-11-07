package ir.masoudkarimi.movies

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.masoudkarimi.movies.resource.MoviesRemoteResource
import ir.masoudkarimi.movies.resource.MoviesRemoteSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun bindMoviesRemoteSource(impl: MoviesRemoteSourceImpl): MoviesRemoteResource

}