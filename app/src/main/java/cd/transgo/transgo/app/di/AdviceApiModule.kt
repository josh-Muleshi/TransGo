package cd.transgo.transgo.app.di

import cd.transgo.transgo.app.service.ApiService
import cd.transgo.transgo.data.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdviceApiModule {

    @Provides
    @Singleton
    fun provideApi(builder:Retrofit.Builder): ApiService{
        return builder
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

}