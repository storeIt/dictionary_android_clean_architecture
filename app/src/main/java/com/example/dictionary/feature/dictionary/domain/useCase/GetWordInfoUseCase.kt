package com.example.dictionary.feature.dictionary.domain.useCase

import com.example.dictionary.feature.dictionary.domain.model.WordInfo
import com.example.dictionary.feature.dictionary.domain.repository.WordInfoRepository
import com.example.dictionary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase(
    private val repository: WordInfoRepository,
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow { }
        }
        return repository.getWordInfo(word)
    }
}
