package com.example.dictionary.feature.dictionary.domain.repository

import com.example.dictionary.feature.dictionary.domain.model.WordInfo
import com.example.dictionary.util.Resource
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}
