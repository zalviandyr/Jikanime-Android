package com.zukron.jikanime.ui.activity.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.zukron.jikanime.R
import com.zukron.jikanime.model.*
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.repository.DetailRepository
import com.zukron.jikanime.repository.LocalRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/13/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */

class DetailViewModel(private val app: Application) : AndroidViewModel(app) {
    private val compositeDisposable = CompositeDisposable()
    private val detailRepository = DetailRepository.getInstance(app)
    private val localRepository = LocalRepository.getInstance(app)
    private val malId: MutableLiveData<Int> = MutableLiveData()
    private val _networkState: MutableLiveData<NetworkState> = detailRepository.networkStateLiveData
    val isLocalCrudSuccess: MutableLiveData<Drawable> = MutableLiveData()
    val networkState: LiveData<NetworkState> = _networkState

    val detailAnime: LiveData<DetailAnimeResponse> = Transformations
            .switchMap(malId) {
                detailRepository.detailAnime(it, compositeDisposable)
            }

    val detailManga: LiveData<DetailMangaResponse> = Transformations
            .switchMap(malId) {
                detailRepository.detailManga(it, compositeDisposable)
            }

    val detailCharacter: LiveData<DetailCharacterResponse> = Transformations
            .switchMap(malId) {
                detailRepository.detailCharacter(it, compositeDisposable)
            }

    val characterList: LiveData<PagedList<CharacterAnimeResponse.Character>> = Transformations
            .switchMap(malId) {
                detailRepository.character(it, compositeDisposable)
            }

    val episodeList: LiveData<PagedList<EpisodeAnimeResponse.Episode>> = Transformations
            .switchMap(malId) {
                detailRepository.episode(it, compositeDisposable)
            }

    fun isCharacterListEmpty(): Boolean {
        return characterList.value?.isEmpty() ?: true
    }

    fun isEpisodeListEmpty(): Boolean {
        return episodeList.value?.isEmpty() ?: true
    }

    /***
     * karena di fragment sebelumnya Network state telah menjadi loaded, maka untuk fragment character
     * dan episode harus direset kalau tidak maka nilai network akan menjadi
     * NetworkState.LOADED (kita tidak ingin ini, ini berasal dari fragment sebelumnya)
     * NetworkState.LOADING
     * NetworkState.LOADED
     */
    fun resetNetworkState() {
        _networkState.value = NetworkState.LOADING
    }

    fun setMalId(id: Int) {
        if (malId.value == id) {
            return
        }
        malId.value = id
    }

    /********************************** LOCAL DATABASE  **********************************/
    val animeMangaFavorite: LiveData<Favorite> = Transformations
            .switchMap(malId) {
                localRepository.get(it, compositeDisposable)
            }

    fun insertFavorite(favorite: Favorite) {
        compositeDisposable.add(
                localRepository.insert(favorite)
                        .subscribe({
                            isLocalCrudSuccess.value = ContextCompat.getDrawable(app, R.drawable.ic_baseline_favorite_24)
                            Toast.makeText(app, "Success add to favorite", Toast.LENGTH_SHORT).show()
                        }, {
                            isLocalCrudSuccess.value = ContextCompat.getDrawable(app, R.drawable.ic_baseline_favorite_border_24)
                            Toast.makeText(app, "Failed add to favorite", Toast.LENGTH_SHORT).show()
                        })
        )
    }

    fun deleteFavorite(malId: Int) =
            compositeDisposable.add(
                    localRepository.delete(malId)
                            .subscribe({
                                isLocalCrudSuccess.value = ContextCompat.getDrawable(app, R.drawable.ic_baseline_favorite_border_24)
                                Toast.makeText(app, "Success delete", Toast.LENGTH_SHORT).show()
                            }, {
                                isLocalCrudSuccess.value = ContextCompat.getDrawable(app, R.drawable.ic_baseline_favorite_24)
                                Toast.makeText(app, "Failed delete", Toast.LENGTH_SHORT).show()
                            })
            )

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}