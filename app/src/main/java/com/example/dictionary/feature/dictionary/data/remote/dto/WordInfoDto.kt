package com.example.dictionary.feature.dictionary.data.remote.dto

import com.example.dictionary.feature.dictionary.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val word: String,
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            word = word,
        )
    }
}
