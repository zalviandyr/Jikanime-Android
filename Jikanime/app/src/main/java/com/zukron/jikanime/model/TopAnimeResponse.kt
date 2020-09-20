package com.zukron.jikanime.model

import com.google.gson.annotations.SerializedName

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/2/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
data class TopAnimeResponse(
        @SerializedName("top")
        val data: List<TopAnimeItem>
) {
    data class TopAnimeItem(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("rank")
            val rank: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("image_url")
            val image: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("episodes")
            val episodes: Int,
            @SerializedName("start_date")
            val startDate: String,
            @SerializedName("end_date")
            val endDate: String,
            @SerializedName("score")
            val score: Double
    )
}