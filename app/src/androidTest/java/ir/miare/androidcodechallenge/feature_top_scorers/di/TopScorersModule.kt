package ir.miare.androidcodechallenge.feature_top_scorers.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import ir.miare.androidcodechallenge.feature_top_scorers.data.FakeTopScorersRepository
import ir.miare.androidcodechallenge.feature_top_scorers.data.remote.TopScorersApi
import ir.miare.androidcodechallenge.feature_top_scorers.data.repository.TopScorersRepositoryImpl
import ir.miare.androidcodechallenge.feature_top_scorers.domain.TopScorersRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [TopScorersModule::class]
)
abstract class TestModule {
    @Singleton
    @Binds
    abstract fun bindsTopScorersRepository(fakeTopScorersRepository: FakeTopScorersRepository): TopScorersRepository
}