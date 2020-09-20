package com.zukron.jikanime.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.zukron.jikanime.model.SearchAnimeResponse.SearchAnimeItem
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.repository.RemoteRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/20/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class SearchAnimeDataSource(
        private val keyword: String,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, SearchAnimeItem>() {

    // first page
    private var page = 1
    private val networkState: MutableLiveData<NetworkState> = remoteRepository.networkState

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, SearchAnimeItem>
    ) {
        compositeDisposable.add(
                remoteRepository.getSearchAnime(page, keyword)
                        .subscribe {
                            callback.onResult(
                                    it.data, null, page + 1
                            )
                            networkState.postValue(NetworkState.LOADED)
                        }
        )
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, SearchAnimeItem>
    ) {
        // who care
    }

    override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, SearchAnimeItem>
    ) {
        compositeDisposable.add(
                remoteRepository.getSearchAnime(params.key, keyword)
                        .subscribe {
                            callback.onResult(
                                    it.data, params.key + 1
                            )
                            networkState.postValue(NetworkState.LOADED)
                        }
        )
    }
}