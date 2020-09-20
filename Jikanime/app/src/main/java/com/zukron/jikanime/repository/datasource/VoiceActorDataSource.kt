package com.zukron.jikanime.repository.datasource

import androidx.paging.PageKeyedDataSource
import com.zukron.jikanime.model.DetailCharacterResponse

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/15/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class VoiceActorDataSource(
        private val voiceActorList: List<DetailCharacterResponse.VoiceActor>
) : PageKeyedDataSource<Int, DetailCharacterResponse.VoiceActor>() {
    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, DetailCharacterResponse.VoiceActor>
    ) {
        callback.onResult(
                voiceActorList, null, null
        )
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, DetailCharacterResponse.VoiceActor>
    ) {
        // who care
    }

    override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, DetailCharacterResponse.VoiceActor>
    ) {
        // who care
    }
}