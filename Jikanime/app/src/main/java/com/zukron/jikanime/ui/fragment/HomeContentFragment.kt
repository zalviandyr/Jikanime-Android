package com.zukron.jikanime.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.FavoriteAdapter
import com.zukron.jikanime.adapter.pagedlist.HomePagedListAdapter
import com.zukron.jikanime.adapter.listener.OnSelectedItemListener
import com.zukron.jikanime.model.*
import com.zukron.jikanime.model.helper.AnimeMangaHelper
import com.zukron.jikanime.model.helper.HomeHelper
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.ui.activity.DetailAnimeActivity
import com.zukron.jikanime.ui.activity.DetailMangaActivity
import com.zukron.jikanime.ui.fragment.viewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_content.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/16/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class HomeContentFragment : Fragment(), OnSelectedItemListener {
    enum class TYPE {
        TOP_ANIME,
        SPRING_ANIME,
        WINTER_ANIME,
        SUMMER_ANIME,
        FALL_ANIME,
        GENRE_ANIME,
        TOP_MANGA,
        GENRE_MANGA,
        FAVORITE
    }

    companion object {
        const val BUNDLE_SUB_TYPE = "sub_type"
        const val BUNDLE_TYPE = "type"

        fun newInstance(subType: String, type: TYPE): HomeContentFragment {
            val contentFragment = HomeContentFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_SUB_TYPE, subType)
            bundle.putSerializable(BUNDLE_TYPE, type)
            contentFragment.arguments = bundle

            return contentFragment
        }
    }

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view model
        homeFragmentViewModel = getHomeFragmentViewModel()

        arguments?.let {
            val subType = it.getString(BUNDLE_SUB_TYPE)
            val type = it.getSerializable(BUNDLE_TYPE)

            // adapter
            val topAnimeAdapter: HomePagedListAdapter<TopAnimeResponse.TopAnimeItem> = HomePagedListAdapter(this)
            val seasonalAnimeAdapter: HomePagedListAdapter<SeasonalAnimeResponse.SeasonalAnimeItem> = HomePagedListAdapter(this)
            val genreAnimeAdapter: HomePagedListAdapter<GenreAnimeResponse.GenreAnimeItem> = HomePagedListAdapter(this)
            val topMangaAdapter: HomePagedListAdapter<TopMangaResponse.TopMangaItem> = HomePagedListAdapter(this)
            val genreMangaAdapter: HomePagedListAdapter<GenreMangaResponse.GenreMangaItem> = HomePagedListAdapter(this)
            val favoriteAdapter = FavoriteAdapter(this)

            when (type) {
                TYPE.TOP_ANIME -> {
                    // recycler view and adapter
                    view.contentFrag_recyclerView.adapter = topAnimeAdapter

                    homeFragmentViewModel.setTopAnimeSubType(subType!!)
                    homeFragmentViewModel.setTypeHomeHelper(HomeHelper.TopAnime)
                    homeFragmentViewModel.topAnimeList.observe(requireActivity()) { pagedList ->
                        topAnimeAdapter.submitList(pagedList)
                    }
                }
                TYPE.SPRING_ANIME -> {
                    // recycler view and adapter
                    view.contentFrag_recyclerView.adapter = seasonalAnimeAdapter

                    homeFragmentViewModel.setSeasonalAnimeYear(subType!!)
                    homeFragmentViewModel.setTypeHomeHelper(HomeHelper.SeasonalAnime.Spring)
                    homeFragmentViewModel.springAnimeList.observe(requireActivity()) { paged ->
                        seasonalAnimeAdapter.submitList(paged)
                    }
                }
                TYPE.WINTER_ANIME -> {
                    // recycler view and adapter
                    view.contentFrag_recyclerView.adapter = seasonalAnimeAdapter

                    homeFragmentViewModel.setSeasonalAnimeYear(subType!!)
                    homeFragmentViewModel.setTypeHomeHelper(HomeHelper.SeasonalAnime.Winter)
                    homeFragmentViewModel.winterAnimeList.observe(requireActivity()) { paged ->
                        seasonalAnimeAdapter.submitList(paged)
                    }
                }
                TYPE.SUMMER_ANIME -> {
                    // recycler view and adapter
                    view.contentFrag_recyclerView.adapter = seasonalAnimeAdapter

                    homeFragmentViewModel.setSeasonalAnimeYear(subType!!)
                    homeFragmentViewModel.setTypeHomeHelper(HomeHelper.SeasonalAnime.Summer)
                    homeFragmentViewModel.summerAnimeList.observe(requireActivity()) { paged ->
                        seasonalAnimeAdapter.submitList(paged)
                    }
                }
                TYPE.FALL_ANIME -> {
                    // recycler view and adapter
                    view.contentFrag_recyclerView.adapter = seasonalAnimeAdapter

                    homeFragmentViewModel.setSeasonalAnimeYear(subType!!)
                    homeFragmentViewModel.setTypeHomeHelper(HomeHelper.SeasonalAnime.Fall)
                    homeFragmentViewModel.fallAnimeList.observe(requireActivity()) { paged ->
                        seasonalAnimeAdapter.submitList(paged)
                    }
                }
                TYPE.GENRE_ANIME -> {
                    // recycler view and adapter
                    view.contentFrag_recyclerView.adapter = genreAnimeAdapter

                    homeFragmentViewModel.setGenreAnimeGenreId(subType!!)
                    homeFragmentViewModel.setTypeHomeHelper(HomeHelper.GenreAnime)
                    homeFragmentViewModel.genreAnimeList.observe(requireActivity()) { paged ->
                        genreAnimeAdapter.submitList(paged)
                    }
                }
                TYPE.TOP_MANGA -> {
                    // recycler view and adapter
                    view.contentFrag_recyclerView.adapter = topMangaAdapter

                    homeFragmentViewModel.setTopMangaSubType(subType!!)
                    homeFragmentViewModel.setTypeHomeHelper(HomeHelper.TopManga)
                    homeFragmentViewModel.topMangaList.observe(requireActivity()) { paged ->
                        topMangaAdapter.submitList(paged)
                    }
                }
                TYPE.GENRE_MANGA -> {
                    // recycler view and adapter
                    view.contentFrag_recyclerView.adapter = genreMangaAdapter

                    homeFragmentViewModel.setGenreMangaGenreId(subType!!)
                    homeFragmentViewModel.setTypeHomeHelper(HomeHelper.GenreManga)
                    homeFragmentViewModel.genreMangaList.observe(requireActivity()) { paged ->
                        genreMangaAdapter.submitList(paged)
                    }
                }
                TYPE.FAVORITE -> {
                    // recycler view and adapter
                    view.contentFrag_recyclerView.adapter = favoriteAdapter

                    homeFragmentViewModel.setFavoriteSubType(subType!!)
                    homeFragmentViewModel.getAllFavorite.observe(requireActivity()) { list ->
                        favoriteAdapter.submitList(list)
                    }
                }
            }

            // network state
            homeFragmentViewModel.networkState.observe(requireActivity()) { network ->
                if (!homeFragmentViewModel.isListEmpty()) {
                    topAnimeAdapter.setNetworkState(network)
                }

                if (network == NetworkState.LOADING) {
                    view.contentFrag_progressBar.visibility = View.VISIBLE
                }

                if (network == NetworkState.LOADED) {
                    view.contentFrag_progressBar.visibility = View.GONE
                }

                if (network == NetworkState.ERROR || network == NetworkState.TIMEOUT || network == NetworkState.NO_CONTENT) {
                    if (network == NetworkState.NO_CONTENT) {
                        view.contentFrag_tvError.visibility = View.VISIBLE
                        view.contentFrag_tvError.text = network.message
                    }

                    Snackbar.make(view, network.message, Snackbar.LENGTH_INDEFINITE)
                            .setAction("Ok") {}
                            .show()
                }
            }
        }
    }

    override fun onDetailIdItem(id: Int?, type: String) {
        arguments?.let {
            val intent: Intent = if (type == AnimeMangaHelper.ANIME) {
                val intentTemp = Intent(requireContext(), DetailAnimeActivity::class.java)
                intentTemp.putExtra(DetailAnimeActivity.EXTRA_ID, id)
                intentTemp
            } else {
                val intentTemp = Intent(requireContext(), DetailMangaActivity::class.java)
                intentTemp.putExtra(DetailMangaActivity.EXTRA_ID, id)
                intentTemp
            }

            startActivity(intent)
        }
    }

    override fun onDetailUrlItem(url: String?) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri

        startActivity(intent)
    }

    private fun getHomeFragmentViewModel(): HomeFragmentViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeFragmentViewModel(requireActivity().application) as T
            }
        }).get(HomeFragmentViewModel::class.java)
    }
}