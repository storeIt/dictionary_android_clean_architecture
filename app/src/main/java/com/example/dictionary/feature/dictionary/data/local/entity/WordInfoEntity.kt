package com.example.dictionary.feature.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionary.feature.dictionary.domain.model.Meaning
import com.example.dictionary.feature.dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val phonetic: String?,
    val meanings: List<Meaning>,
    @PrimaryKey val id: Int? = null,
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(word = word, phonetic = phonetic, meanings = meanings)
    }
}
