package com.zukron.jikanime.ui.fragment.detailanime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zukron.jikanime.R
import com.zukron.jikanime.model.DetailAnimeResponse
import com.zukron.jikanime.model.Favorite
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.service.Utilities
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_anime1.view.*

class DetailAnime1Fragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private var detailAnimeResponse: DetailAnimeResponse? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_anime1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view model
        detailViewModel = requireActivity().let {
            ViewModelProvider(it).get(DetailViewModel::class.java)
        }

        detailViewModel.animeMangaFavorite.observe(requireActivity()) {
            if (it == null) {
                view.detailAnime1Frag_fabFavorite.setImageDrawable(
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_border_24))
            } else {
                view.detailAnime1Frag_fabFavorite.setImageDrawable(
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_favorite_24))
            }
        }

        detailViewModel.isLocalCrudSuccess.observe(requireActivity()) {
            view.detailAnime1Frag_fabFavorite.setImageDrawable(it)
        }

        detailViewModel.detailAnime.observe(requireActivity()) {
            // set detail anime response
            detailAnimeResponse = it

            view.detailAnime1Frag_tvSynopsis.text = it.synopsis
            view.detailAnime1Frag_tvTitle.text = it.title
            view.detailAnime1Frag_ratingBar.rating = (it.score / 2.0).toFloat()
            view.detailAnime1Frag_tvScore.text = it.score.toString()
            view.detailAnime1Frag_tvRank.text = it.rank.toString()
            view.detailAnime1Frag_tvType.text = it.type
            view.detailAnime1Frag_tvSource.text = it.source
            view.detailAnime1Frag_tvEpisode.text = it.episodes.toString()
            view.detailAnime1Frag_tvScoreBy.text = it.scoredBy.toString()
            view.detailAnime1Frag_tvStatus.text = it.status
            view.detailAnime1Frag_tvPremiered.text = it.premiered
            view.detailAnime1Frag_tvAired.text = Utilities.concatDate(
                    Utilities.formatDate(it.aired.from),
                    Utilities.formatDate(it.aired.to))
            view.detailAnime1Frag_tvDuration.text = it.duration
            view.detailAnime1Frag_tvRating.text = it.rating

            Glide.with(requireContext())
                    .load(it.imageUrl)
                    .placeholder(R.drawable.icons8_no_image_100)
                    .into(view.detailAnime1Frag_imageView)
        }

        detailViewModel.networkState.observe(requireActivity()) {
            if (it == NetworkState.LOADING) {
                view.detailAnime1Frag_content.visibility = View.INVISIBLE
                view.detailAnime1Frag_progressBar.visibility = View.VISIBLE
            }

            if (it == NetworkState.LOADED) {
                view.detailAnime1Frag_content.visibility = View.VISIBLE
                view.detailAnime1Frag_progressBar.visibility = View.GONE
            }

            if (it == NetworkState.TIMEOUT) {
                Snackbar.make(view.detailAnime1Frag_content, it.message, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ok") {}
                        .show()
            }
        }

        // favorite action
        view.detailAnime1Frag_fabFavorite.setOnClickListener {
            detailAnimeResponse?.let {
                if (detailViewModel.animeMangaFavorite.value != null) {
                    // delete action
                    deleteAction(it.malId)
                } else {
                    val favorite = Favorite(it.malId, Favorite.ANIME, it.imageUrl, it.title, it.score.toString(), it.url)
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