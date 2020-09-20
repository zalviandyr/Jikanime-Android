package com.zukron.jikanime.model

import com.google.gson.annotations.SerializedName

data class SearchAnimeResponse(
		@SerializedName("results")
		val data: List<SearchAnimeItem>
) {
    data class SearchAnimeItem(
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
			@SerializedName("start_date")
			val startDate: String,
			@SerializedName("end_date")
			val endDate: String,
			@SerializedName("episodes")
			val episodes: Int
	)
}