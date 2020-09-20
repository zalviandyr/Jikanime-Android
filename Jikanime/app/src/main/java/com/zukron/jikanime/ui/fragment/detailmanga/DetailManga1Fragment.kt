package com.zukron.jikanime.ui.fragment.detailmanga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zukron.jikanime.R
import com.zukron.jikanime.model.DetailMangaResponse
import com.zukron.jikanime.model.Favorite
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.service.Utilities
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_manga1.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class DetailManga1Fragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private var detailMangaResponse: DetailMangaResponse? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_detail_manga1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view model
        detailViewModel = requireActivity().let {
            ViewModelProvider(it).get(DetailViewModel::class.java)
        }

        detailViewModel.animeMangaFavorite.observe(requireActivity()) {
            if (it == null) {
                detailManga1Frag_fabFavorite.setImageDrawable(
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_border_24))
            } else {
                detailManga1Frag_fabFavorite.setImageDrawable(
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_24))
            }
        }

        detailViewModel.isLocalCrudSuccess.observe(requireActivity()) {
            detailManga1Frag_fabFavorite.setImageDrawable(it)
        }

        detailViewModel.detailManga.observe(requireActivity()) {
            // set detail manga response
            detailMangaResponse = it

            detailManga1Frag_tvSynopsis.text = it.synopsis
            detailManga1Frag_tvTitle.text = it.title
            detailManga1Frag_ratingBar.rating = (it.score / 2.0).toFloat()
            detailManga1Frag_tvScore.text = it.score.toString()
            detailManga1Frag_tvRank.text = it.rank.toString()
            detailManga1Frag_tvType.text = it.type
            detailManga1Frag_tvChapter.text = it.chapters.toString()
            detailManga1Frag_tvVolumes.text = it.volumes.toString()
            detailManga1Frag_tvScoreBy.text = it.scoredBy.toString()
            detailManga1Frag_tvStatus.text = it.status
            detailManga1Frag_tvPublished.text = Utilities.concatDate(
                    Utilities.formatDate(it.published.from),
                    Utilities.formatDate(it.published.to)
            )

            Glide.with(requireContext())
                    .load(it.imageUrl)
                    .placeholder(R.drawable.icons8_no_image_100)
                    .into(detailManga1Frag_imageView)
        }

        detailViewModel.networkState.observe(requireActivity()) {
            if (it == NetworkState.LOADING) {
                detailManga1Frag_content.visibility = View.INVISIBLE
                detailManga1Frag_progressBar.visibility = View.VISIBLE
            }

            if (it == NetworkState.LOADED) {
                detailManga1Frag_content.visibility = View.VISIBLE
                detailManga1Frag_progressBar.visibility = View.GONE
            }

            if (it == NetworkState.TIMEOUT) {
                Snackbar.make(detailManga1Frag_content, it.message, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ok") {}
                        .show()
            }
        }

        // favorite action
        detailManga1Frag_fabFavorite.setOnClickListener {
            detailMangaResponse?.let {
                if (detailViewModel.animeMangaFavorite.value != null) {
                    // delete action
                    deleteAction(it.malId)
                } else {
                    val favorite = Favorite(it.malId, Favorite.MANGA, it.imageUrl, it.title, it.score.toString(), it.url)
                    detailViewModel.insertFavorite(favorite)
                }
            }
        }
    }

    private fun deleteAction(malId: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle("Delete")
                .setMessage("Are you sure to delete it from favorite ?")
                .setPositiveButton("Yes") { _, _ ->
                    detailViewModel.deleteFavorite(malId)
                }
                .setNegativeButton("No") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
        alertDialog.show()
    }
}