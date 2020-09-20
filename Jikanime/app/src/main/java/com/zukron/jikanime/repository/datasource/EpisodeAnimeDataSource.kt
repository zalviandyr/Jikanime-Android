package com.zukron.jikanime.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.zukron.jikanime.model.EpisodeAnimeResponse.Episode
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.repository.RemoteRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class EpisodeAnimeDataSource(
        private val malId: Int,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Episode>() {

    // first page
    private var page = 1
    private val networkState: MutableLiveData<NetworkState> = remoteRepository.networkState

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Episode>
    ) {
        compositeDisposable.add(
                remoteRepository.getEpisode(page, malId)
                        .subscribe {
                            callback.onResult(
                                    it.episodes, null, page + 1
                            )
                            networkState.postValue(NetworkState.LOADED)
                        }
        )
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Episode>
    ) {
        // who care
    }

    override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Episode>
    ) {
        compositeDisposable.add(
                remoteRepository.getEpisode(params.key, malId)
                        .subscribe {
                            callback.onResult(
                                    it.episodes, params.key + 1
                            )
                            networkState.postValue(NetworkState.LOADED)
                        }
        )
    }
}