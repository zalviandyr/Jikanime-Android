package com.zukron.jikanime.repository.factory

import androidx.paging.DataSource
import com.zukron.jikanime.model.GenreAnimeResponse.GenreAnimeItem
import com.zukron.jikanime.repository.RemoteRepository
import com.zukron.jikanime.repository.datasource.GenreAnimeDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/12/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class GenreAnimeDataFactory(
        private val genreId: String,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, GenreAnimeItem>() {

    override fun create(): DataSource<Int, GenreAnimeItem> {
        val genreAnimeDataSource = GenreAnimeDataSource(genreId, remoteRepository, compositeDisposable)

        return genreAnimeDataSource.mapByPage {
            it.sortedWith { previousItem, nextItem -> nextItem.score.compareTo(previousItem.score) }
        }
    }
}