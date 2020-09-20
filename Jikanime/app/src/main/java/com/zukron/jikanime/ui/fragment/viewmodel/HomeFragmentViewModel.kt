package com.zukron.jikanime.ui.fragment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.zukron.jikanime.model.*
import com.zukron.jikanime.model.helper.HomeHelper
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.repository.HomeRepository
import com.zukron.jikanime.repository.LocalRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/17/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private val homeRepository = HomeRepository.getInstance(application)
    private val localRepository = LocalRepository.getInstance(application)
    private val topAnimeSubType: MutableLiveData<String> = MutableLiveData()
    private val seasonalAnimeYear: MutableLiveData<String> = MutableLiveData()
    private val genreAnimeGenreId: MutableLiveData<String> = MutableLiveData()
    private val topMangaSubType: MutableLiveData<String> = MutableLiveData()
    private val genreMangaGenreId: MutableLiveData<String> = MutableLiveData()
    private val favoriteSubType: MutableLiveData<String> = MutableLiveData()
    private val typeHomeHelper: MutableLiveData<HomeHelper> = MutableLiveData()

    val networkState: LiveData<NetworkState> = homeRepository.networkStateLiveData

    /********************************** TOP ANIME  **********************************/
    val topAnimeList: LiveData<PagedList<TopAnimeResponse.TopAnimeItem>> = Transformations
            .switchMap(topAnimeSubType) {
                homeRepository.topAnimeList(it, compositeDisposable)
            }

    fun setTopAnimeSubType(subType: String) {
        if (topAnimeSubType.value == subType) {
            return
        }
        topAnimeSubType.value = subType
    }

    /********************************** SPRING ANIME  **********************************/
    val springAnimeList: LiveData<PagedList<SeasonalAnimeResponse.SeasonalAnimeItem>> = Transformations
            .switchMap(seasonalAnimeYear) {
                homeRepository.springAnime(it, compositeDisposable)
            }

    /********************************** WINTER ANIME  **********************************/
    val winterAnimeList: LiveData<PagedList<SeasonalAnimeResponse.SeasonalAnimeItem>> = Transformations
            .switchMap(seasonalAnimeYear) {
                homeRepository.winterAnime(it, compositeDisposable)
            }

    /********************************** SUMMER ANIME  **********************************/
    val summerAnimeList: LiveData<PagedList<SeasonalAnimeResponse.SeasonalAnimeItem>> = Transformations
            .switchMap(seasonalAnimeYear) {
                homeRepository.summerAnime(it, compositeDisposable)
            }

    /********************************** FALL ANIME  **********************************/
    val fallAnimeList: LiveData<PagedList<SeasonalAnimeResponse.SeasonalAnimeItem>> = Transformations
            .switchMap(seasonalAnimeYear) {
                homeRepository.fallAnime(it, compositeDisposable)
            }

    fun setSeasonalAnimeYear(year: String) {
        if (seasonalAnimeYear.value == year) {
            return
        }
        seasonalAnimeYear.value = year
    }

    /********************************** GENRE ANIME  **********************************/
    val genreAnimeList: LiveData<PagedList<GenreAnimeResponse.GenreAnimeItem>> = Transformations
            .switchMap(genreAnimeGenreId) {
                homeRepository.genreAnime(it, compositeDisposable)
            }

    fun setGenreAnimeGenreId(genreId: String) {
        if (genreAnimeGenreId.value == genreId) {
            return
        }
        genreAnimeGenreId.value = genreId
    }

    /********************************** TOP Manga  **********************************/
    val topMangaList: LiveData<PagedList<TopMangaResponse.TopMangaItem>> = Transformations
            .switchMap(topMangaSubType) {
                homeRepository.topManga(it, compositeDisposable)
            }

    fun setTopMangaSubType(subType: String) {
        if (topMangaSubType.value == subType) {
            return
        }
        topMangaSubType.value = subType
    }

    /********************************** GENRE MANGA  **********************************/
    val genreMangaList: LiveData<PagedList<GenreMangaResponse.GenreMangaItem>> = Transformations
            .switchMap(genreMangaGenreId) {
                homeRepository.genreManga(it, compositeDisposable)
            }

    fun setGenreMangaGenreId(genreId: String) {
        if (genreMangaGenreId.value == genreId) {
            return
        }
        genreMangaGenreId.value = genreId
    }

    /********************************** FAVORITE  **********************************/
    val getAllFavorite: LiveData<List<Favorite>> = Transformations
            .switchMap(favoriteSubType) {
                localRepository.getAll(it, compositeDisposable)
            }

    fun setFavoriteSubType(subType: String) {
        if (favoriteSubType.value == subType) {
            return
        }
        favoriteSubType.value = subType
    }

    /**********************************************************************************/

    /***
     * helper untuk mengetahui jenis home helper yang akan dipakai untuk mengecek list kosong
     * atau tidak
     */
    fun setTypeHomeHelper(homeHelper: HomeHelper) {
        if (typeHomeHelper.value == homeHelper) {
            return
        }
        typeHomeHelper.value = homeHelper
    }

    fun isListEmpty(): Boolean {
        var empty = true
        typeHomeHelper.value.let {
            when (it) {
                is HomeHelper.TopAnime -> {
                    empty = topAnimeList.value?.isEmpty() ?: true
                }
                is HomeHelper.SeasonalAnime.Spring -> {
                    empty = springAnimeList.value?.isEmpty() ?: true
                }
                is HomeHelper.SeasonalAnime.Summer -> {
                    empty = summerAnimeList.value?.isEmpty() ?: true
                }
                is HomeHelper.SeasonalAnime.Winter -> {
                    empty = winterAnimeList.value?.isEmpty() ?: true
                }
                is HomeHelper.SeasonalAnime.Fall -> {
                    empty = fallAnimeList.value?.isEmpty() ?: true
                }
                is HomeHelper.TopManga -> {
                    empty = topMangaList.value?.isEmpty() ?: true
                }
                is HomeHelper.GenreManga -> {
                    empty = genreMangaList.value?.isEmpty() ?: true
                }
            }
        }

        return empty
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}