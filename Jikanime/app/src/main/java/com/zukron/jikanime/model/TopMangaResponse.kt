package com.zukron.jikanime.model

import com.google.gson.annotations.SerializedName

data class TopMangaResponse(
		@SerializedName("top")
		val data: List<TopMangaItem>
) {
    data class TopMangaItem(
			@SerializedName("end_date")
			val endDate: String,
			@SerializedName("score")
			val score: Double,
			@SerializedName("image_url")
			val imageUrl: String,
			@SerializedName("volumes")
			val volumes: Int,
			@SerializedName("rank")
			val rank: Int,
			@SerializedName("mal_id")
			val malId: Int,
			@SerializedName("title")
			val title: String,
			@SerializedName("type")
			val type: String,
			@SerializedName("url")
			val url: String,
			@SerializedName("start_date")
			val startDate: String,
	)
}
