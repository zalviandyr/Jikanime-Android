package com.zukron.jikanime.repository.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.zukron.jikanime.model.SeasonalAnimeResponse.SeasonalAnimeItem
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.repository.RemoteRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/11/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class SeasonalAnimeDataSource(
        private val remoteRepository: RemoteRepository,
        private val year: String,
        private val season: String,
        private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, SeasonalAnimeItem>() {

    private val TAG = SeasonalAnimeDataSource::class.java.simpleName
    private val networkState: MutableLiveData<NetworkState> = remoteRepository.networkState

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, SeasonalAnimeItem>
    ) {
        compositeDisposable.add(
                remoteRepository.getSeasonalAnime(year, season)
                        .subscribe({
                            callback.onResult(
                                    it.data, null, null
                            )
                            networkState.postValue(NetworkState.LOADED)
                        }, {
                            Log.e(TAG, "loadInitial: $it")
                        })
        )
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, SeasonalAnimeItem>
    ) {
        // who care
    }

    override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, SeasonalAnimeItem>
    ) {
        // who care
    }
}