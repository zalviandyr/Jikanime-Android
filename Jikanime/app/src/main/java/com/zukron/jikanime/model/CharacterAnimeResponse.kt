package com.zukron.jikanime.model


import com.google.gson.annotations.SerializedName

data class CharacterAnimeResponse(
        @SerializedName("characters")
        val data: List<Character>
) {
    data class Character(
            @SerializedName("image_url")
            val imageUrl: String,
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("role")
            val role: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("voice_actors")
            val voiceActors: List<VoiceActor>
    )

    data class VoiceActor(
            @SerializedName("image_url")
            val imageUrl: String,
            @SerializedName("language")
            val language: String,
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
    )
}
