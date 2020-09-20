package com.zukron.jikanime.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.zukron.jikanime.local.FavoriteDatabase
import com.zukron.jikanime.model.Favorite
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/19/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class LocalRepository(context: Context) {
    companion object {
        private const val DB_NAME = "favorite_db"

        @Volatile
        private var INSTANCE: LocalRepository? = null

        fun getInstance(context: Context): LocalRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: LocalRepository(context).also { INSTANCE = it }
                }
    }

    private val database: FavoriteDatabase by lazy {
        Room.databaseBuilder(
                context,
                FavoriteDatabase::class.java,
                DB_NAME
        ).build()
    }

    fun get(malId: Int, compositeDisposable: CompositeDisposable): LiveData<Favorite> {
        return object : LiveData<Favorite>() {
            override fun onActive() {
                super.onActive()

                compositeDisposable.add(
                        database.favoriteDao().getByMalId(malId)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ value = it }, { value = null })
                )
            }
        }
    }

    fun getAll(subType: String, compositeDisposable: CompositeDisposable): LiveData<List<Favorite>> {
        return object : LiveData<List<Favorite>>() {
            override fun onActive() {
                super.onActive()

                if (subType == Favorite.ANIME) {
                    compositeDisposable.add(
                            database.favoriteDao().getAllAnime()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({ value = it }, { value = null })
                    )
                } else if (subType == Favorite.MANGA) {
                    compositeDisposable.add(
                            database.favoriteDao().getAllManga()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({ value = it }, { value = null })
                    )
                }
            }
        }
    }

    fun insert(favorite: Favorite): Completable {
        return database.favoriteDao().insert(favorite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(malId: Int): Completable {
        return database.favoriteDao().delete(malId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}