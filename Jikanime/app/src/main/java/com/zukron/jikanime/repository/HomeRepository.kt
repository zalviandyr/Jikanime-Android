package com.zukron.jikanime.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zukron.jikanime.model.*
import com.zukron.jikanime.model.helper.HomeHelper
import com.zukron.jikanime.repository.factory.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.Executors

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/16/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class HomeRepository(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: HomeRepository? = null

        fun getInstance(context: Context): HomeRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: HomeRepository(context).also { INSTANCE = it }
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

    // top anime
    fun topAnimeList(
            subType: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<TopAnimeResponse.TopAnimeItem>> {
        val topAnimeDataFactory = TopAnimeDataFactory(subType, repository, compositeDisposable)

        return LivePagedListBuilder(topAnimeDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // spring anime
    fun springAnime(
            year: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<SeasonalAnimeResponse.SeasonalAnimeItem>> {
        val season = HomeHelper.SeasonalAnime.Spring.toString()
        val seasonalAnimeDataFactory = SeasonalAnimeDataFactory(year, season, repository, compositeDisposable)

        return LivePagedListBuilder(seasonalAnimeDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // winter anime
    fun winterAnime(
            year: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<SeasonalAnimeResponse.SeasonalAnimeItem>> {
        val season = HomeHelper.SeasonalAnime.Winter.toString()
        val seasonalAnimeDataFactory = SeasonalAnimeDataFactory(year, season, repository, compositeDisposable)

        return LivePagedListBuilder(seasonalAnimeDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // summer anime
    fun summerAnime(
            year: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<SeasonalAnimeResponse.SeasonalAnimeItem>> {
        val season = HomeHelper.SeasonalAnime.Summer.toString()
        val seasonalAnimeDataFactory = SeasonalAnimeDataFactory(year, season, repository, compositeDisposable)

        return LivePagedListBuilder(seasonalAnimeDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // fall anime
    fun fallAnime(
            year: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<SeasonalAnimeResponse.SeasonalAnimeItem>> {
        val season = HomeHelper.SeasonalAnime.Fall.toString()
        val seasonalAnimeDataFactory = SeasonalAnimeDataFactory(year, season, repository, compositeDisposable)

        return LivePagedListBuilder(seasonalAnimeDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // genre anime
    fun genreAnime(
            genreId: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<GenreAnimeResponse.GenreAnimeItem>> {
        val genreAnimeDataFactory = GenreAnimeDataFactory(genreId, repository, compositeDisposable)

        return LivePagedListBuilder(genreAnimeDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // top manga
    fun topManga(
            subType: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<TopMangaResponse.TopMangaItem>> {
        val topMangaDataFactory = TopMangaDataFactory(subType, repository, compositeDisposable)

        return LivePagedListBuilder(topMangaDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // genre manga
    fun genreManga(
            genreId: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<GenreMangaResponse.GenreMangaItem>> {
        val genreMangaDataFactory = GenreMangaDataFactory(genreId, repository, compositeDisposable)

        return LivePagedListBuilder(genreMangaDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // search anime
    fun searchAnime(
            keyword: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<SearchAnimeResponse.SearchAnimeItem>> {
        val searchAnimeDataFactory = SearchAnimeDataFactory(keyword, repository, compositeDisposable)

        return LivePagedListBuilder(searchAnimeDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }

    // search manga
    fun searchManga(
            keyword: String,
            compositeDisposable: CompositeDisposable
    ): LiveData<PagedList<SearchMangaResponse.SearchMangaItem>> {
        val searchMangaDataFactory = SearchMangaDataFactory(keyword, repository, compositeDisposable)

        return LivePagedListBuilder(searchMangaDataFactory, pageListConfig)
                .setFetchExecutor(executor)
                .build()
    }
}