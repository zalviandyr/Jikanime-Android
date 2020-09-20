package com.zukron.jikanime.model


import com.google.gson.annotations.SerializedName

data class SearchMangaResponse(
        @SerializedName("results")
        val data: List<SearchMangaItem>
) {
    data class SearchMangaItem(
            @SerializedName("image_url")
            val imageUrl: String,
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("start_date")
            val startDate: String,
            @SerializedName("end_date")
            val endDate: String,
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