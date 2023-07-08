package com.example.dictionary.feature.dictionary.data.repository

import com.example.dictionary.constant.networking.NO_INTERNET_CONNECTION
import com.example.dictionary.constant.networking.WORD_FETCH_FAILED
import com.example.dictionary.feature.dictionary.data.local.WordInfoDao
import com.example.dictionary.feature.dictionary.data.remote.DictionaryApi
import com.example.dictionary.feature.dictionary.domain.model.WordInfo
import com.example.dictionary.feature.dictionary.domain.repository.WordInfoRepository
import com.example.dictionary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao,
) : WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
		
        val wordInfo = dao.getWord(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfo))
		
        try {
            val remoteWordInfo = api.fetchWord(word)
            dao.deleteWords(remoteWordInfo.map { it.word })
            dao.insertWords(remoteWordInfo.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(WORD_FETCH_FAILED, data = wordInfo))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    NO_INTERNET_CONNECTION,
                    data = wordInfo,
                ),
            )
        }
		
        val newWordInfo = dao.getWord(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfo))
    }
}
