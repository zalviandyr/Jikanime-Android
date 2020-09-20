package com.zukron.jikanime.model


import com.google.gson.annotations.SerializedName

data class GenreMangaResponse(
        @SerializedName("manga")
        val data: List<GenreMangaItem>
) {
    data class GenreMangaItem(
            @SerializedName("image_url")
            val imageUrl: String,
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("publishing_start")
            val publishingStart: String,
            @SerializedName("score")
            val score: Double,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("volumes")
            val volumes: Int
    )
}