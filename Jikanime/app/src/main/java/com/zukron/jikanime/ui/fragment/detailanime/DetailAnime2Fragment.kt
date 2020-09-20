package com.zukron.jikanime.ui.fragment.detailanime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.ChipAdapter
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_anime2.view.*

class DetailAnime2Fragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_anime2, container, false)
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
                it.genres.isEmpty() -> view.detailAnime2Frag_rvGenre.visibility = View.GONE
                it.producers.isEmpty() -> view.detailAnime2Frag_rvProducers.visibility = View.GONE
                it.studios.isEmpty() -> view.detailAnime2Frag_rvStudio.visibility = View.GONE
                it.licensors.isEmpty() -> view.detailAnime2Frag_rvLicensors.visibility = View.GONE
            }

            val genreAdapter = ChipAdapter()
            genreAdapter.dataList = it.genres
            view.detailAnime2Frag_rvGenre.adapter = genreAdapter

            val producerAdapter = ChipAdapter()
            producerAdapter.dataList = it.producers
            view.detailAnime2Frag_rvProducers.adapter = producerAdapter

            val studioAdapter = ChipAdapter()
            studioAdapter.dataList = it.studios
            view.detailAnime2Frag_rvStudio.adapter = studioAdapter

            val licensorAdapter = ChipAdapter()
            licensorAdapter.dataList = it.licensors
            view.detailAnime2Frag_rvLicensors.adapter = licensorAdapter
        }
    }
}