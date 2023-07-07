package com.example.dictionary.feature.dictionary.data.remote

import com.example.dictionary.feature.dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("/api/v2/entries/en/{word}")
    suspend fun fetchWord(@Path("word") word: String): List<WordInfoDto>
}
