package com.zukron.jikanime.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zukron.jikanime.model.*
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.repository.factory.CharacterAnimeDataFactory
import com.zukron.jikanime.repository.factory.EpisodeAnimeDataFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.Executors

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/19/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class DetailRepository(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: DetailRepository? = null

        fun getInstance(context: Context): DetailRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: DetailRepository(context).also { INSTANCE = it }
                }
    }

    private val postPerPage = 10
    private val repository = RemoteRepository.getInstance(context)
    private val executor = Executors.newFixedThreadPool(5)
    private val pageListConfig = PagedList.Config.Builder()
            .setPageSize(postPerPage)
            .setEnablePlaceholders(false)
            .build()

    // network state
    val networkStateLiveData = repository.networkState

    fun detailAnime(
            malId: Int,
            compositeDisposable: CompositeDisposable
    ): LiveData<DetailAnimeResponse> {
        return object : LiveData<DetailAnimeResponse>() {
            override fun onActive() {
                super.onActive()

                compositeDisposable.add(
                        repository.getDetailAnime(malId)
                                .subscribe {
                                    value = it
                                    networkStateLiveData.postValue(NetworkState.LOADED)
                                }
                )
            }
        }
    }

    fun detailManga(
            malId: Int,
            compositeDisposable: CompositeDisposable
    ): LiveData<DetailMangaResponse> {
        return object : LiveData<DetailMangaResponse>() {
            override fun onActive() {
                super.onActive()

                compositeDisposable.add(
                        repository.getDetailManga(malId)
                                .subscribe {
                                    value = it
                                    networkStateLiveData.postValue(NetworkState.LOADED)
                                }
                )
            }
        }
    }

    fun detailCharacter(
            malId: Int,
            compositeDisposable: CompositeDisposable
    ): LiveData<DetailCharacterResponse> {
        return object : LiveData<DetailCharacterResponse>() {
            override fun onActive() {
                super.onActive()

                compositeDisposable.add(
                        repository.getDetailCharacter(malId)
                                .subscribe {
                                    value = it
                                    networkStateLiveData.postValue(NetworkState.LOADED)
                                }
                )
            }
        }
    }

    // character
    fun character(
            malId: Int,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<CharacterAnimeResponse.Character>> {
        val characterDataFactory = CharacterAnimeDataFactory(malId, repository, compositeDisposable)

        return LivePagedListBuilder(characterDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // episode
    fun episode(
            malId: Int,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<EpisodeAnimeResponse.Episode>> {
        val episodeDataFactory = EpisodeAnimeDataFactory(malId, repository, compositeDisposable)

        return LivePagedListBuilder(episodeDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }
}