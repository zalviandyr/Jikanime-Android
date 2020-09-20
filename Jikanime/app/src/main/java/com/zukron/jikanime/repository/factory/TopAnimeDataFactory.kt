package com.zukron.jikanime.repository.factory

import androidx.paging.DataSource
import com.zukron.jikanime.repository.datasource.TopAnimeDataSource
import com.zukron.jikanime.model.TopAnimeResponse.TopAnimeItem
import com.zukron.jikanime.repository.RemoteRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/10/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class TopAnimeDataFactory(
        private val subType: String,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, TopAnimeItem>() {

    override fun create(): DataSource<Int, TopAnimeItem> {
        return TopAnimeDataSource(subType, remoteRepository, compositeDisposable)
    }
}