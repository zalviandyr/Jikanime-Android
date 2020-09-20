package com.zukron.jikanime.ui.fragment.detailcharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zukron.jikanime.R
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_character1.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/15/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class DetailCharacter1Fragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_character1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view model
        detailViewModel = requireActivity().let {
            ViewModelProvider(it).get(DetailViewModel::class.java)
        }

        detailViewModel.detailCharacter.observe(requireActivity()) {
            view.detailCharacter1Frag_tvAbout.text = it.about
            view.detailCharacter1Frag_tvTitle.text = it.name

            Glide.with(requireContext())
                    .load(it.imageUrl)
                    .placeholder(R.drawable.icons8_no_image_100)
                    .into(view.detailCharacter1Frag_imageView)
        }

        detailViewModel.networkState.observe(requireActivity()) {
            if (it == NetworkState.LOADING) {
                view.detailCharacter1Frag_content.visibility = View.INVISIBLE
                view.detailCharacter1Frag_progressBar.visibility = View.VISIBLE
            }

            if (it == NetworkState.LOADED) {
                view.detailCharacter1Frag_content.visibility = View.VISIBLE
                view.detailCharacter1Frag_progressBar.visibility = View.GONE
            }

            if (it == NetworkState.TIMEOUT) {
                Snackbar.make(view.detailCharacter1Frag_content, it.message, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ok") {}
                        .show()
            }
        }
    }
}