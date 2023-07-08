package com.example.dictionary.util.di

import android.app.Application
import androidx.room.Room
import com.example.dictionary.feature.dictionary.data.local.WordInfoDatabase
import com.example.dictionary.feature.dictionary.data.remote.DictionaryApi
import com.example.dictionary.feature.dictionary.data.repository.WordInfoRepositoryImpl
import com.example.dictionary.feature.dictionary.domain.repository.WordInfoRepository
import com.example.dictionary.feature.dictionary.domain.useCase.GetWordInfoUseCase
import com.example.dictionary.util.json.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {
	
    @Provides
    @Singleton
    fun providerGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfoUseCase {
        return GetWordInfoUseCase(repository)
    }
	
    @Provides
    @Singleton
    fun provideWordInfoRepository(api: DictionaryApi, db: WordInfoDatabase): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }
	
    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "dictionary_db",
        )
            .addTypeConverter(GsonParser(Gson()))
            .build()
    }
	
    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}
