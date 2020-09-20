package com.zukron.jikanime.model

import com.google.gson.annotations.SerializedName

data class EpisodeAnimeResponse(
        @SerializedName("episodes")
        val episodes: List<Episode>
) {
    data class Episode(
            @SerializedName("aired")
            val aired: String,
            @SerializedName("episode_id")
            val episodeId: Int,
            @SerializedName("recap")
            val recap: Boolean,
            @SerializedName("title")
            val title: String,
            @SerializedName("title_romanji")
            val titleRomanji: String,
            @SerializedName("video_url")
            val videoUrl: String
    )
}