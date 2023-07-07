package com.example.dictionary.feature.dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionary.feature.dictionary.domain.model.Meaning
import com.example.dictionary.util.json.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converter(
    private val jsonParser: JsonParser,
) {
    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson<List<Meaning>>(json, object : TypeToken<List<Meaning>>() {}.type)
            ?: emptyList()
    }
	
    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<List<Meaning>>() {}.type,
        ) ?: ""
    }
}
