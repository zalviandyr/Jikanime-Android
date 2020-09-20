package com.zukron.jikanime.model.helper

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/19/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
data class AnimeMangaHelper(
        val title: String,
        val rank: String,
        val score: String,
        val episodesOrVolumes: String,
        val type: String,
        val date: String,
        val image: String,
        val url: String,
        val malId: Int
) {
    companion object {
        const val ANIME = "anime"
        const val MANGA = "manga"
    }
}