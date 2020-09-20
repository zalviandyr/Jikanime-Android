package com.zukron.jikanime.repository.factory

import androidx.paging.DataSource
import com.zukron.jikanime.model.TopMangaResponse.TopMangaItem
import com.zukron.jikanime.repository.RemoteRepository
import com.zukron.jikanime.repository.datasource.TopMangaDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/12/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class TopMangaDataFactory(
        private val subType: String,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, TopMangaItem>() {

    override fun create(): DataSource<Int, TopMangaItem> {
        return TopMangaDataSource(subType, remoteRepository, compositeDisposable)
    }
}