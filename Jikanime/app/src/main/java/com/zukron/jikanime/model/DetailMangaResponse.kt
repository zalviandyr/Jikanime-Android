package com.zukron.jikanime.model


import com.google.gson.annotations.SerializedName

data class DetailMangaResponse(
        @SerializedName("authors")
        val authors: List<Author>,
        @SerializedName("chapters")
        val chapters: Int,
        @SerializedName("genres")
        val genres: List<Genre>,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("mal_id")
        val malId: Int,
        @SerializedName("published")
        val published: Published,
        @SerializedName("rank")
        val rank: Int,
        @SerializedName("related")
        val related: Related,
        @SerializedName("score")
        val score: Double,
        @SerializedName("scored_by")
        val scoredBy: Int,
        @SerializedName("serializations")
        val serializations: List<Serialization>,
        @SerializedName("status")
        val status: String,
        @SerializedName("synopsis")
        val synopsis: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("volumes")
        val volumes: Int
) {
    data class Author(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
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

    data class Serialization(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
    )

    data class Published(
            @SerializedName("from")
            val from: String,
            @SerializedName("to")
            val to: String
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