package com.example.dictionary.feature.dictionary.presentation.state

import com.example.dictionary.feature.dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false,
)
