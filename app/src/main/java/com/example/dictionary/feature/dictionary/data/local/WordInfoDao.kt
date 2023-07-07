package com.example.dictionary.feature.dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionary.feature.dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordInfoEntity>)
	
    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWords(words: List<String>)
	
    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%'  || :word || '%'")
    suspend fun getWord(word: String): List<WordInfoEntity>
}
