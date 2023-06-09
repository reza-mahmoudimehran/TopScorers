package ir.miare.androidcodechallenge.feature_top_scorers.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.miare.androidcodechallenge.feature_top_scorers.data.remote.TopScorersApi
import ir.miare.androidcodechallenge.feature_top_scorers.data.repository.TopScorersRepositoryImpl
import ir.miare.androidcodechallenge.feature_top_scorers.domain.TopScorersRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TopScorersModule {
    companion object {
        @Singleton
        @Provides
        fun provideTopScorersApi(retrofit: Retrofit): TopScorersApi =
            retrofit.create(TopScorersApi::class.java)

    }
    @Singleton
    @Binds
    abstract fun bindsTopScorersRepository(topScorersRepositoryImpl: TopScorersRepositoryImpl): TopScorersRepository
}