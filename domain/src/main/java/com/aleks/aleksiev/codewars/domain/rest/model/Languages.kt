package com.aleks.aleksiev.codewars.domain.rest.model

import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject

data class Languages(val languages: List<Language>) {

    companion object {
        @JvmStatic
        val languageRanPropertyName = "rank"
        @JvmStatic
        val languageScorePropertyName = "score"
        @JvmStatic
        fun getDeserializer(): JsonDeserializer<Languages> {

            return JsonDeserializer { json, _, _ ->
                val jsonObject = json as? JsonObject
                if (jsonObject != null ) {
                    val languages = mutableListOf<Language>()
                    for(languageName in jsonObject.keySet()) {
                        val languageObject = jsonObject[languageName] as? JsonObject
                        if (languageObject != null) {
                            if (languageObject.has(languageRanPropertyName)
                                && languageObject.has(languageScorePropertyName)) {
                                val rank = languageObject[languageRanPropertyName].asInt
                                val score = languageObject[languageScorePropertyName].asInt
                                languages.add(Language(
                                    languageName = languageName,
                                    rank = rank,
                                    score = score
                                ))
                            }
                        }
                    }
                    Languages(languages)
                } else {
                    Languages(emptyList())
                }
            }
        }
    }
}