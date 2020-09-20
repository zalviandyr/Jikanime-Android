package com.zukron.jikanime.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/19/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
@Entity(tableName = "favorite")
data class Favorite(
        @PrimaryKey(autoGenerate = false)
        val malId: Int,
        @ColumnInfo(name = "type")
        val type: String,
        @ColumnInfo(name = "image_url")
        val imageUrl: String,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "score")
        val score: String,
        @ColumnInfo(name = "url")
        val url: String
) {
    companion object {
        const val ANIME = "anime"
        const val MANGA = "manga"
    }
}