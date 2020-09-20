package com.zukron.jikanime.model


import com.google.gson.annotations.SerializedName

data class DetailCharacterResponse(
    @SerializedName("about")
    val about: String,
    @SerializedName("animeography")
    val animeography: List<Animeography>,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("mangaography")
    val mangaography: List<Mangaography>,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("voice_actors")
    val voiceActors: List<VoiceActor>
) {
    data class Animeography(
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("mal_id")
        val malId: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("role")
        val role: String,
        @SerializedName("url")
        val url: String
    )

    data class Mangaography(
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("mal_id")
        val malId: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("role")
        val role: String,
        @SerializedName("url")
        val url: String
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