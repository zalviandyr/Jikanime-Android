package com.zukron.jikanime.repository.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.zukron.jikanime.model.TopAnimeResponse.TopAnimeItem
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.repository.RemoteRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/10/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class TopAnimeDataSource(
        private val subType: String,
        private val remoteRepository: RemoteRepository,
        private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, TopAnimeItem>() {

    // first page
    private var page = 1
    private val TAG = TopAnimeDataSource::class.java.simpleName
    private val networkState: MutableLiveData<NetworkState> = remoteRepository.networkState

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, TopAnimeItem>
    ) {
        compositeDisposable.add(
                remoteRepository.getTopAnime(page, subType)
                        .subscribe({
                            callback.onResult(
                                    it.data, null, page + 1
                            )
                            networkState.postValue(NetworkState.LOADED)
                        }, {
                            Log.e(TAG, "loadInitial: $it")
                        })
        )
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, TopAnimeItem>
    ) {
        // who care
    }

    override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, TopAnimeItem>
    ) {
        compositeDisposable.add(
                remoteRepository.getTopAnime(params.key, subType)
                        .subscribe({
                            callback.onResult(
                                    it.data, params.key + 1
                            )
                            networkState.postValue(NetworkState.LOADED)
                        }, {
                            Log.e(TAG, "loadAfter: $it")
                        })
        )
    }
}