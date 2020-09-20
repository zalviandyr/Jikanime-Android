package com.zukron.jikanime.ui.fragment.detailanime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.pagedlist.EpisodeAnimePagedListAdapter
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_anime_paged.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class DetailAnime6Fragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_anime_paged, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adapter
        val adapter = EpisodeAnimePagedListAdapter()
        view.detailAnimePaged_recyclerView.adapter = adapter

        // title
        view.detailAnimePaged_tvTitle.text = getString(R.string.episode)

        // view model
        val detailAnimeViewModel = requireActivity().let {
            ViewModelProvider(it).get(DetailViewModel::class.java)
        }
        detailAnimeViewModel.resetNetworkState()

        detailAnimeViewModel.episodeList.observe(requireActivity()) {
            adapter.submitList(it)
        }

        detailAnimeViewModel.networkState.observe(requireActivity()) {
            if (!detailAnimeViewModel.isEpisodeListEmpty()) {
                adapter.setNetworkState(it)
            }

            if (it == NetworkState.LOADED) {
                view.detailAnimePaged_content.visibility = View.VISIBLE
                view.detailAnimePaged_progressBar.visibility = View.GONE
            }

            if (it == NetworkState.ERROR || it == NetworkState.TIMEOUT) {
                Snackbar.make(view.detailAnimePaged_content, it.message, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ok") {}
                        .show()
            }
        }
    }
}