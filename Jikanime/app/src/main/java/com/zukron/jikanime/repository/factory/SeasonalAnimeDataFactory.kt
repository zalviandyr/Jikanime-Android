package com.zukron.jikanime.repository.factory

import androidx.paging.DataSource
import com.zukron.jikanime.model.SeasonalAnimeResponse.SeasonalAnimeItem
import com.zukron.jikanime.repository.RemoteRepository
import com.zukron.jikanime.repository.datasource.SeasonalAnimeDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/11/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class SeasonalAnimeDataFactory(
        private val year: String,
        private val season: String,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, SeasonalAnimeItem>() {

    override fun create(): DataSource<Int, SeasonalAnimeItem> {
        val seasonalAnimeDataSource = SeasonalAnimeDataSource(remoteRepository, year, season, compositeDisposable)

        // compare higher score
        return seasonalAnimeDataSource.mapByPage {
            it.sortedWith { previousItem, nextItem -> nextItem!!.score.compareTo(previousItem!!.score) }
        }
    }
}