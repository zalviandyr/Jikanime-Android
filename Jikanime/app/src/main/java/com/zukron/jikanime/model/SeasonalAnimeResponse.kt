package com.zukron.jikanime.model

import com.google.gson.annotations.SerializedName

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/11/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
data class SeasonalAnimeResponse(
		@SerializedName("anime")
		val data: List<SeasonalAnimeItem>
) {
    data class SeasonalAnimeItem(
			@SerializedName("image_url")
			val imageUrl: String,
			@SerializedName("mal_id")
			val malId: Int,
			@SerializedName("title")
			val title: String,
			@SerializedName("type")
			val type: String,
			@SerializedName("url")
			val url: String,
			@SerializedName("score")
			val score: Double,
			@SerializedName("airing_start")
			val airingStart: String,
			@SerializedName("episodes")
			val episodes: Int,
	)
}