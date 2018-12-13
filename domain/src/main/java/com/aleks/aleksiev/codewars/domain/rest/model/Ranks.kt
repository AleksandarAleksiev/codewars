package com.aleks.aleksiev.codewars.domain.rest.model

import com.google.gson.annotations.SerializedName

data class Ranks(
    @SerializedName("overall")
    val rank: Rank,
    @SerializedName("languages")
    val languages: Languages
)