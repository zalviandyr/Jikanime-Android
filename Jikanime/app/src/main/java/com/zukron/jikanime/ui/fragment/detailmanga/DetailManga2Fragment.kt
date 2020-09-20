package com.zukron.jikanime.ui.fragment.detailmanga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.ChipAdapter
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_manga2.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class DetailManga2Fragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_manga2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view model
        detailViewModel = requireActivity().let {
            ViewModelProvider(it).get(DetailViewModel::class.java)
        }

        detailViewModel.detailManga.observe(requireActivity()) {
            // hidden when empty
            when {
                it.authors.isEmpty() -> detailManga2Frag_rvAuthors.visibility = View.GONE
                it.genres.isEmpty() -> detailManga2Frag_rvGenre.visibility = View.GONE
                it.serializations.isEmpty() -> detailManga2Frag_rvSerialization.visibility = View.GONE
            }

            val authorAdapter = ChipAdapter()
            authorAdapter.dataList = it.authors
            detailManga2Frag_rvAuthors.adapter = authorAdapter

            val genreAdapter = ChipAdapter()
            genreAdapter.dataList = it.genres
            detailManga2Frag_rvGenre.adapter = genreAdapter

            val serializationAdapter = ChipAdapter()
            serializationAdapter.dataList = it.serializations
            detailManga2Frag_rvSerialization.adapter = serializationAdapter
        }
    }
}