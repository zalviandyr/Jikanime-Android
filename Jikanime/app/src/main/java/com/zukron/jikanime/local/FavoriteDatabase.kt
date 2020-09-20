package com.zukron.jikanime.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zukron.jikanime.model.Favorite

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/19/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}