package com.zukron.jikanime.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.zukron.jikanime.model.*
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.network.RestApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/16/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class RemoteRepository(context: Context) {
    private val apiService = RestApi.getApiService(context)
    val networkState: MutableLiveData<NetworkState> = RestApi.networkStateMutableLiveData

    companion object {
        @Volatile
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(context: Context): RemoteRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: RemoteRepository(context).also { INSTANCE = it }
                }
    }

    fun getTopAnime(page: Int, subType: String): Flowable<TopAnimeResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getTopAnime(page, subType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .delay(1, TimeUnit.SECONDS)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            networkState.postValue(NetworkState.NO_CONTENT)
                            false
                        }
                    }
                }
    }

    fun getSeasonalAnime(year: String, season: String): Flowable<SeasonalAnimeResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getSeasonalAnime(year, season)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .delay(1, TimeUnit.SECONDS)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            networkState.postValue(NetworkState.NO_CONTENT)
                            false
                        }
                    }
                }
    }

    fun getGenreAnime(page: Int, genreId: String): Flowable<GenreAnimeResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getGenreAnime(genreId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .delay(1, TimeUnit.SECONDS)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            networkState.postValue(NetworkState.NO_CONTENT)
                            false
                        }
                    }
                }
    }

    fun getTopManga(page: Int, subType: String): Flowable<TopMangaResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getTopManga(page, subType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .delay(1, TimeUnit.SECONDS)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            networkState.postValue(NetworkState.NO_CONTENT)
                            false
                        }
                    }
                }
    }

    fun getGenreManga(page: Int, genreId: String): Flowable<GenreMangaResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getGenreManga(genreId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .delay(1, TimeUnit.SECONDS)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            networkState.postValue(NetworkState.NO_CONTENT)
                            false
                        }
                    }
                }

    }

    fun getCharacter(malId: Int): Flowable<CharacterAnimeResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getCharacterAnime(malId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .delay(1, TimeUnit.SECONDS)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            false
                        }
                    }
                }
    }

    fun getEpisode(page: Int, malId: Int): Flowable<EpisodeAnimeResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getEpisodeAnime(malId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .delay(1, TimeUnit.SECONDS)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            false
                        }
                    }
                }
    }

    fun getDetailAnime(malId: Int): Flowable<DetailAnimeResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getDetailAnime(malId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            false
                        }
                    }
                }
    }

    fun getDetailManga(malId: Int): Flowable<DetailMangaResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getDetailManga(malId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            false
                        }
                    }
                }
    }

    fun getDetailCharacter(malId: Int): Flowable<DetailCharacterResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getDetailCharacter(malId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            false
                        }
                    }
                }
    }

    fun getSearchAnime(page: Int, keyword: String): Flowable<SearchAnimeResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getSearchAnime(keyword, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            networkState.postValue(NetworkState.NO_CONTENT)
                            false
                        }
                    }
                }
    }

    fun getSearchManga(page: Int, keyword: String): Flowable<SearchMangaResponse> {
        networkState.postValue(NetworkState.LOADING)

        return apiService.getSearchManga(keyword, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 1)
                .timeout(5, TimeUnit.SECONDS)
                .retryWhen {
                    it.takeWhile { v ->
                        return@takeWhile if (v is TimeoutException || v is SocketTimeoutException) {
                            networkState.postValue(NetworkState.TIMEOUT)
                            true
                        } else {
                            networkState.postValue(NetworkState.NO_CONTENT)
                            false
                        }
                    }
                }
    }
}