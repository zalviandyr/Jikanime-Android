package com.zukron.jikanime.repository.factory

import androidx.paging.DataSource
import com.zukron.jikanime.model.SearchMangaResponse.SearchMangaItem
import com.zukron.jikanime.repository.RemoteRepository
import com.zukron.jikanime.repository.datasource.SearchAnimeDataSource
import com.zukron.jikanime.repository.datasource.SearchMangaDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/10/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class SearchMangaDataFactory(
        private val keyword: String,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, SearchMangaItem>() {

    override fun create(): DataSource<Int, SearchMangaItem> {
        return SearchMangaDataSource(keyword, remoteRepository, compositeDisposable)
    }
}