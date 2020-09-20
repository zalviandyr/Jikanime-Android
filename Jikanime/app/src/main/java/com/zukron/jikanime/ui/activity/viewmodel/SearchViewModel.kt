package com.zukron.jikanime.ui.activity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.zukron.jikanime.model.SearchAnimeResponse
import com.zukron.jikanime.model.SearchMangaResponse
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.repository.HomeRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/20/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private val homeRepository = HomeRepository.getInstance(application)
    private val keywordAnime: MutableLiveData<String> = MutableLiveData()
    private val keywordManga: MutableLiveData<String> = MutableLiveData()

    val networkState: LiveData<NetworkState> = homeRepository.networkStateLiveData

    /********************************** SEARCH ANIME  **********************************/
    val searchAnimeList: LiveData<PagedList<SearchAnimeResponse.SearchAnimeItem>> = Transformations
            .switchMap(keywordAnime) {
                homeRepository.searchAnime(it, compositeDisposable)
            }

    fun setKeywordAnime(keyword: String) {
        if (keywordAnime.value == keyword) {
            return
        }
        keywordAnime.value = keyword
    }

    fun isSearchAnimeListEmpty(): Boolean {
        return searchAnimeList.value?.isEmpty() ?: true
    }

    /********************************** SEARCH MANGA  **********************************/
    val searchMangaList: LiveData<PagedList<SearchMangaResponse.SearchMangaItem>> = Transformations
            .switchMap(keywordManga) {
                homeRepository.searchManga(it, compositeDisposable)
            }

    fun setKeywordManga(keyword: String) {
        if (keywordManga.value == keyword) {
            return
        }
        keywordManga.value = keyword
    }

    fun isSearchMangaListEmpty(): Boolean {
        return searchMangaList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}