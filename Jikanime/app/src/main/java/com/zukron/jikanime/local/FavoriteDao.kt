package com.zukron.jikanime.local

import androidx.room.*
import com.zukron.jikanime.model.Favorite
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/19/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getAll(): Flowable<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE malId = (:malId)")
    fun getByMalId(malId: Int): Single<Favorite>

    @Query("SELECT * FROM favorite WHERE type = 'anime'")
    fun getAllAnime(): Flowable<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE type = 'manga'")
    fun getAllManga(): Flowable<List<Favorite>>

    @Insert
    fun insert(favorite: Favorite): Completable

    @Query("DELETE FROM favorite WHERE malId = (:malId)")
    fun delete(malId: Int): Completable
}