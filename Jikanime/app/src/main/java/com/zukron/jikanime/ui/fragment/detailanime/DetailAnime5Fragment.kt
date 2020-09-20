package com.zukron.jikanime.ui.fragment.detailanime

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.listener.OnSelectedItemListener
import com.zukron.jikanime.adapter.pagedlist.CharacterAnimePagedListAdapter
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.ui.activity.DetailCharacterActivity
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_anime_paged.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class DetailAnime5Fragment : Fragment(), OnSelectedItemListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_anime_paged, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adapter
        val adapter = CharacterAnimePagedListAdapter()
        adapter.setOnPageItemListener(this)
        view.detailAnimePaged_recyclerView.adapter = adapter

        // title
        view.detailAnimePaged_tvTitle.text = getString(R.string.character)

        // view model
        val detailAnimeViewModel = requireActivity().let {
            ViewModelProvider(it).get(DetailViewModel::class.java)
        }
        detailAnimeViewModel.resetNetworkState()

        detailAnimeViewModel.characterList.observe(requireActivity()) { paged ->
            adapter.submitList(paged)
        }

        detailAnimeViewModel.networkState.observe(requireActivity()) { network ->
            if (!detailAnimeViewModel.isCharacterListEmpty()) {
                adapter.setNetworkState(network)
            }

            if (network == NetworkState.LOADED) {
                view.detailAnimePaged_content.visibility = View.VISIBLE
                view.detailAnimePaged_progressBar.visibility = View.GONE
            }

            if (network == NetworkState.ERROR || network == NetworkState.TIMEOUT) {
                Snackbar.make(view.detailAnimePaged_content, network.message, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ok") {}
                        .show()
            }
        }
    }

    override fun onDetailIdItem(id: Int?, type: String) {
        val intent = Intent(context, DetailCharacterActivity::class.java)
        intent.putExtra(DetailCharacterActivity.EXTRA_ID, id)

        startActivity(intent)
    }

    override fun onDetailUrlItem(url: String?) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri

        startActivity(intent)
    }
}