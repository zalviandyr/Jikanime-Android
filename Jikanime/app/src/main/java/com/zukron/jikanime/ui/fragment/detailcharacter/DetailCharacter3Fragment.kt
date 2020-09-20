package com.zukron.jikanime.ui.fragment.detailcharacter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.listener.OnSelectedItemListener
import com.zukron.jikanime.adapter.pagedlist.AnimeographyPagedListAdapter
import com.zukron.jikanime.repository.datasource.AnimeographyDataSource
import com.zukron.jikanime.ui.activity.DetailAnimeActivity
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_anime_paged.view.*
import java.util.concurrent.Executors

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/15/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class DetailCharacter3Fragment : Fragment(), OnSelectedItemListener {
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_anime_paged, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // paged builder
        val postPerPage = 10
        val executor = Executors.newFixedThreadPool(5)
        val pageListConfig = PagedList.Config.Builder()
                .setPageSize(postPerPage)
                .setEnablePlaceholders(false)
                .build()
        val adapter = AnimeographyPagedListAdapter()
        adapter.setOnPagedItemListener(this)

        view.detailAnimePaged_tvTitle.text = getString(R.string.animeography)

        // view model
        detailViewModel = requireActivity().let {
            ViewModelProvider(it).get(DetailViewModel::class.java)
        }

        detailViewModel.detailCharacter.observe(requireActivity()) {
            val animeographyDataSource = AnimeographyDataSource(it.animeography)
            val builder = PagedList.Builder(animeographyDataSource, pageListConfig)
                    .setFetchExecutor(executor)
                    .setNotifyExecutor(executor)
                    .build()
            adapter.submitList(builder)

            view.detailAnimePaged_content.visibility = View.VISIBLE
            view.detailAnimePaged_progressBar.visibility = View.GONE
            view.detailAnimePaged_recyclerView.adapter = adapter
        }
    }

    override fun onDetailIdItem(id: Int?, type: String) {
        val intent = Intent(context, DetailAnimeActivity::class.java)
        intent.putExtra(DetailAnimeActivity.EXTRA_ID, id)

        startActivity(intent)
    }

    override fun onDetailUrlItem(url: String?) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri

        startActivity(intent)
    }
}