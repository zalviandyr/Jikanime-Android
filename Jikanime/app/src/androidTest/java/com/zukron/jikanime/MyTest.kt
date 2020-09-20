package com.zukron.jikanime

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.zukron.jikanime.local.FavoriteDatabase
import com.zukron.jikanime.model.Favorite
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/2/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
@RunWith(AndroidJUnit4::class)
class MyTest {
    private lateinit var database: FavoriteDatabase
    private val TAG = "MyTest"

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context,
                FavoriteDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun testingGet() {
        val favorite = Favorite(123, "anime")
        database.favoriteDao().insert(favorite)
                .subscribe({
                    Log.d(TAG, "testingGet: Insert Success")
                }, {
                    Log.d(TAG, "testingGet: Insert Failed")
                })

        database.favoriteDao().getByMalId(123)
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG, "testingGet: SUCSEESS")
                }, {
                    Log.d(TAG, "testingGet: ERROR")
                })
    }
}