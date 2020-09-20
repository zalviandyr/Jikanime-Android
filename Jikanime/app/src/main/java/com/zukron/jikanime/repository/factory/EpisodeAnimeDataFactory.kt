package com.zukron.jikanime.repository.factory

import androidx.paging.DataSource
import com.zukron.jikanime.model.EpisodeAnimeResponse.Episode
import com.zukron.jikanime.repository.RemoteRepository
import com.zukron.jikanime.repository.datasource.EpisodeAnimeDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class EpisodeAnimeDataFactory(
        private val malId: Int,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Episode>() {

    override fun create(): DataSource<Int, Episode> {
        return EpisodeAnimeDataSource(malId, remoteRepository, compositeDisposable)
    }
}