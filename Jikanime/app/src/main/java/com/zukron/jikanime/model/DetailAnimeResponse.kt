package com.zukron.jikanime.model


import com.google.gson.annotations.SerializedName

data class DetailAnimeResponse(
        @SerializedName("aired")
        val aired: Aired,
        @SerializedName("duration")
        val duration: String,
        @SerializedName("ending_themes")
        val endingThemes: List<String>,
        @SerializedName("episodes")
        val episodes: Int,
        @SerializedName("genres")
        val genres: List<Genre>,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("licensors")
        val licensors: List<Licensor>,
        @SerializedName("mal_id")
        val malId: Int,
        @SerializedName("opening_themes")
        val openingThemes: List<String>,
        @SerializedName("premiered")
        val premiered: String,
        @SerializedName("producers")
        val producers: List<Producer>,
        @SerializedName("rank")
        val rank: Int,
        @SerializedName("rating")
        val rating: String,
        @SerializedName("related")
        val related: Related,
        @SerializedName("score")
        val score: Double,
        @SerializedName("scored_by")
        val scoredBy: Int,
        @SerializedName("source")
        val source: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("studios")
        val studios: List<Studio>,
        @SerializedName("synopsis")
        val synopsis: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String
) {
    data class Aired(
            @SerializedName("from")
            val from: String,
            @SerializedName("to")
            val to: String
    )

    data class Genre(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
    )

    data class Licensor(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
    )

    data class Producer(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
    )

    data class Studio(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
    )

    data class Related(
            @SerializedName("Adaptation")
            val adaptation: List<Adaptation>?,
            @SerializedName("Side story")
            val sideStory: List<SideStory>?,
            @SerializedName("Summary")
            val summary: List<Summary>?,
            @SerializedName("Sequel")
            val sequel: List<Sequel>?,
            @SerializedName("Prequel")
            val prequel: List<Prequel>?,
            @SerializedName("Spin-off")
            val spinOff: List<SpinOff>?
    ) {
        data class Adaptation(
                @SerializedName("mal_id")
                val malId: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("type")
                val type: String,
                @SerializedName("url")
                val url: String
        )

        data class SideStory(
                @SerializedName("mal_id")
                val malId: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("type")
                val type: String,
                @SerializedName("url")
                val url: String
        )

        data class Summary(
                @SerializedName("mal_id")
                val malId: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("type")
                val type: String,
                @SerializedName("url")
                val url: String
        )

        data class Sequel(
                @SerializedName("mal_id")
                val malId: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("type")
                val type: String,
                @SerializedName("url")
                val url: String
        )

        data class Prequel(
                @SerializedName("mal_id")
                val malId: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("type")
                val type: String,
                @SerializedName("url")
                val url: String
        )

        data class SpinOff(
                @SerializedName("mal_id")
                val malId: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("type")
                val type: String,
                @SerializedName("url")
                val url: String
        )
    }
}