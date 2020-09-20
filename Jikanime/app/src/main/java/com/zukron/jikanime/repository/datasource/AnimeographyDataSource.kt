package com.zukron.jikanime.repository.datasource

import androidx.paging.PageKeyedDataSource
import com.zukron.jikanime.model.DetailCharacterResponse

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/15/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class AnimeographyDataSource(
        private val animeographyList: List<DetailCharacterResponse.Animeography>
) : PageKeyedDataSource<Int, DetailCharacterResponse.Animeography>() {
    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, DetailCharacterResponse.Animeography>
    ) {
        callback.onResult(
                animeographyList, null, null
        )
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, DetailCharacterResponse.Animeography>
    ) {
        // who care
    }

    override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, DetailCharacterResponse.Animeography>
    ) {
        // who care
    }
}