package ir.masoudkarimi.feature_flag

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ir.masoudkarimi.feature_flag.config.DefaultProviderSorter
import ir.masoudkarimi.feature_flag.providers.FeatureFlagProvider
import ir.masoudkarimi.feature_flag.providers.FirebaseFlagProvider
import ir.masoudkarimi.feature_flag.providers.GrowthBookFlagProvider
import ir.masoudkarimi.feature_flag.config.ProviderSorter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureFlagModule {

    @Binds
    @Singleton
    abstract fun bindFeatureFlagRepository(impl: FeatureFlagRepositoryImpl): FeatureFlagRepository

    @Binds
    abstract fun bindProviderSorter(impl: DefaultProviderSorter): ProviderSorter

    @Binds
    @IntoSet
    abstract fun bindGrowthBookFlagProvider(impl: GrowthBookFlagProvider): FeatureFlagProvider

    @Binds
    @IntoSet
    abstract fun bindFirebaseFlagProvider(impl: FirebaseFlagProvider): FeatureFlagProvider
}