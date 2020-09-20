package com.zukron.jikanime.repository.factory

import androidx.paging.DataSource
import com.zukron.jikanime.model.GenreMangaResponse.GenreMangaItem
import com.zukron.jikanime.repository.RemoteRepository
import com.zukron.jikanime.repository.datasource.GenreMangaDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/12/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class GenreMangaDataFactory(
        private val genreId: String,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, GenreMangaItem>() {

    override fun create(): DataSource<Int, GenreMangaItem> {
        val genreMangaDataSource = GenreMangaDataSource(genreId, remoteRepository, compositeDisposable)

        return genreMangaDataSource.mapByPage {
            it.sortedWith { previousItem, nextItem -> nextItem.score.compareTo(previousItem.score) }
        }
    }
}