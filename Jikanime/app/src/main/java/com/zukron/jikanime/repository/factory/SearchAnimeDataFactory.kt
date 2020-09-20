package com.zukron.jikanime.repository.factory

import androidx.paging.DataSource
import com.zukron.jikanime.model.SearchAnimeResponse.SearchAnimeItem
import com.zukron.jikanime.repository.RemoteRepository
import com.zukron.jikanime.repository.datasource.SearchAnimeDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/10/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class SearchAnimeDataFactory(
        private val keyword: String,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, SearchAnimeItem>() {

    override fun create(): DataSource<Int, SearchAnimeItem> {
        return SearchAnimeDataSource(keyword, remoteRepository, compositeDisposable)
    }
}