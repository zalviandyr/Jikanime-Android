package com.zukron.jikanime.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.listener.OnSelectedItemListener
import com.zukron.jikanime.adapter.pagedlist.SearchPagedListAdapter
import com.zukron.jikanime.model.SearchAnimeResponse
import com.zukron.jikanime.model.SearchMangaResponse
import com.zukron.jikanime.model.helper.AnimeMangaHelper
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.ui.activity.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener, OnSelectedItemListener {
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAnimeAdapter: SearchPagedListAdapter<SearchAnimeResponse.SearchAnimeItem>
    private lateinit var searchMangaAdapter: SearchPagedListAdapter<SearchMangaResponse.SearchMangaItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // status bar color
        changeStatusBarColor()

        // adapter
        searchAnimeAdapter = SearchPagedListAdapter(this)
        searchMangaAdapter = SearchPagedListAdapter(this)

        // view model
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        searchViewModel.searchAnimeList.observe(this) {
            searchAnimeAdapter.submitList(it)
            searchAct_recyclerView.adapter = searchAnimeAdapter
        }

        searchViewModel.searchMangaList.observe(this) {
            searchMangaAdapter.submitList(it)
            searchAct_recyclerView.adapter = searchMangaAdapter
        }

        searchViewModel.networkState.observe(this) {
            if (searchAct_btnAnime.isChecked){
                if (!searchViewModel.isSearchAnimeListEmpty()) {
                    searchAnimeAdapter.setNetworkState(it)
                }
            }

            if (searchAct_btnManga.isChecked) {
                if (!searchViewModel.isSearchMangaListEmpty()) {
                    searchMangaAdapter.setNetworkState(it)
                }
            }

            if (it == NetworkState.LOADING) {
                searchAct_progressBar.visibility = View.VISIBLE
            }

            if (it == NetworkState.LOADED) {
                searchAct_progressBar.visibility = View.GONE
            }

            if (it == NetworkState.ERROR || it == NetworkState.TIMEOUT || it == NetworkState.NO_CONTENT) {
                if (it == NetworkState.NO_CONTENT) {
                    searchAct_tvError.visibility = View.VISIBLE
                    searchAct_tvError.text = it.message
                }

                Snackbar.make(searchAct_recyclerView, it.message, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ok") {}
                        .show()
            }
        }

        // search view
        searchAct_searchView.setOnQueryTextListener(this)

        // button listener
        searchAct_btnAnime.setOnClickListener {
            searchAct_btnManga.isChecked = false
        }

        searchAct_btnManga.setOnClickListener {
            searchAct_btnAnime.isChecked = false
        }
    }

    private fun changeStatusBarColor() {
        val window = window
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorMainAlpha70)
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        searchAct_searchView.clearFocus()

        return if (!searchAct_btnAnime.isChecked && !searchAct_btnManga.isChecked) {
            Toast.makeText(this, "Choose anime or manga", Toast.LENGTH_SHORT).show()
            false
        } else if (searchAct_btnAnime.isChecked) {
            searchViewModel.setKeywordAnime(text)
            true
        } else if (searchAct_btnManga.isChecked) {
            searchViewModel.setKeywordManga(text)
            true
        } else {
            false
        }
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        // who care
        return false
    }

    override fun onDetailIdItem(id: Int?, type: String) {
        val intent: Intent = if (type == AnimeMangaHelper.ANIME) {
            val intentTemp = Intent(this, DetailAnimeActivity::class.java)
            intentTemp.putExtra(DetailAnimeActivity.EXTRA_ID, id)
            intentTemp
        } else {
            val intentTemp = Intent(this, DetailMangaActivity::class.java)
            intentTemp.putExtra(DetailMangaActivity.EXTRA_ID, id)
            intentTemp
        }

        startActivity(intent)
    }

    override fun onDetailUrlItem(url: String?) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri

        startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
    }
}