package com.zukron.jikanime.model

import com.google.gson.annotations.SerializedName

data class GenreAnimeResponse(
		@SerializedName("anime")
		val data: List<GenreAnimeItem>
) {
    data class GenreAnimeItem(
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
			val episodes: Int
	)
}