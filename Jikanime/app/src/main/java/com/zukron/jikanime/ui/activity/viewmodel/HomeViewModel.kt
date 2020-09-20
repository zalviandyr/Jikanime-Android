package com.zukron.jikanime.ui.activity.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zukron.jikanime.model.helper.HomeHelper
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.network.RestApi
import com.zukron.jikanime.ui.fragment.HomeContentFragment

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/16/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class HomeViewModel : ViewModel() {
    private val _homeHelper: MutableLiveData<HomeHelper> = MutableLiveData()
    val networkStateLiveData: LiveData<NetworkState> = RestApi.networkStateMutableLiveData

    val fragmentListLiveData: LiveData<List<Fragment>> = Transformations
            .switchMap(_homeHelper) {
                val fragmentList: MutableList<Fragment> = mutableListOf()
                val mutableLiveData: MutableLiveData<List<Fragment>> = MutableLiveData()

                if (it is HomeHelper.TopAnime) {
                    val titles = it.topAnimeTitles
                    for (title in titles) {
                        fragmentList.add(HomeContentFragment.newInstance(title, HomeContentFragment.TYPE.TOP_ANIME))
                    }
                }

                if (it is HomeHelper.SeasonalAnime) {
                    val titles = it.yearList
                    val season = when (it) {
                        is HomeHelper.SeasonalAnime.Winter -> {
                            HomeContentFragment.TYPE.WINTER_ANIME
                        }
                        is HomeHelper.SeasonalAnime.Fall -> {
                            HomeContentFragment.TYPE.FALL_ANIME
                        }
                        is HomeHelper.SeasonalAnime.Summer -> {
                            HomeContentFragment.TYPE.SUMMER_ANIME
                        }
                        is HomeHelper.SeasonalAnime.Spring -> {
                            HomeContentFragment.TYPE.SPRING_ANIME
                        }
                    }

                    for (title in titles) {
                        fragmentList.add(HomeContentFragment.newInstance(title, season))
                    }
                }

                if (it is HomeHelper.GenreAnime) {
                    val idList = it.genreList.map { genre -> genre.id }
                    for (id in idList) {
                        fragmentList.add(HomeContentFragment.newInstance(id, HomeContentFragment.TYPE.GENRE_ANIME))
                    }
                }

                if (it is HomeHelper.TopManga) {
                    val titles = it.topMangaTitles
                    for (title in titles) {
                        fragmentList.add(HomeContentFragment.newInstance(title, HomeContentFragment.TYPE.TOP_MANGA))
                    }
                }

                if (it is HomeHelper.GenreManga) {
                    val idList = it.genreList.map { genre -> genre.id }
                    for (id in idList) {
                        fragmentList.add(HomeContentFragment.newInstance(id, HomeContentFragment.TYPE.GENRE_MANGA))
                    }
                }

                if (it is HomeHelper.Favorite) {
                    val titles = it.favoriteTitles
                    for (title in titles) {
                        fragmentList.add(HomeContentFragment.newInstance(title, HomeContentFragment.TYPE.FAVORITE))
                    }
                }


                mutableLiveData.value = fragmentList
                mutableLiveData
            }

    val titleListLiveData: LiveData<List<String>> = Transformations
            .switchMap(_homeHelper) {
                val mutableLiveData: MutableLiveData<List<String>> = MutableLiveData()

                mutableLiveData.value = when (it) {
                    is HomeHelper.TopAnime -> it.topAnimeTitles
                    is HomeHelper.SeasonalAnime -> it.yearList
                    is HomeHelper.GenreAnime -> it.genreList.map { genre -> genre.name }
                    is HomeHelper.TopManga -> it.topMangaTitles
                    is HomeHelper.GenreManga -> it.genreList.map { genre -> genre.name }
                    is HomeHelper.Favorite -> it.favoriteTitles
                }

                mutableLiveData
            }

    fun setHomeHelper(homeHelper: HomeHelper) {
        if (_homeHelper.value == homeHelper) {
            return
        }

        _homeHelper.value = homeHelper
    }
}