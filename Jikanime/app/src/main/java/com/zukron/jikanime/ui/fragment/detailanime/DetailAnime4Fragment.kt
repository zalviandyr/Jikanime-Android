package com.zukron.jikanime.ui.fragment.detailanime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.SongAdapter
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_anime4.view.*

class DetailAnime4Fragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_anime4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view model
        detailViewModel = requireActivity().let {
            ViewModelProvider(it).get(DetailViewModel::class.java)
        }

        detailViewModel.detailAnime.observe(requireActivity()) {
            // hidden when empty
            when {
                it.openingThemes.isEmpty() -> view.detailAnime4Frag_rvOpening.visibility = View.GONE
                it.endingThemes.isEmpty() -> view.detailAnime4Frag_rvEnding.visibility = View.GONE
            }

            val openingAdapter = SongAdapter()
            openingAdapter.dataList = it.openingThemes
            view.detailAnime4Frag_rvOpening.adapter = openingAdapter

            val endingAdapter = SongAdapter()
            endingAdapter.dataList = it.endingThemes
            view.detailAnime4Frag_rvEnding.adapter = endingAdapter
        }
    }
}