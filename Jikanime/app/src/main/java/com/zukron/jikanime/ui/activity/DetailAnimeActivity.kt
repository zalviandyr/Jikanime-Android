package com.zukron.jikanime.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.ViewPagerAdapter
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import com.zukron.jikanime.ui.fragment.detailanime.*
import com.zukron.jikanime.ui.fragment.viewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailAnimeActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID: String = "extra_id"
    }

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // change status bar color
        changeStatusBarColor()

        // toolbar
        detailAct_materialToolbar.title = getString(R.string.detail_anime)
        setSupportActionBar(detailAct_materialToolbar)

        // view model
        val id = intent.getIntExtra(EXTRA_ID, 0)
        detailViewModel = getViewModel()
        detailViewModel.setMalId(id)

        // view pager 2
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        val fragmentList = listOf(
                DetailAnime1Fragment(),
                DetailAnime2Fragment(),
                DetailAnime3Fragment(),
                DetailAnime4Fragment(),
                DetailAnime5Fragment(),
                DetailAnime6Fragment()
        )

        // adapter
        adapter.fragmentList = fragmentList
        detailAct_viewPager2.adapter = adapter

        // dot indicator
        detailAct_dotsIndicator.setViewPager2(detailAct_viewPager2)
    }


    private fun getViewModel(): DetailViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetailViewModel(application) as T
            }
        }).get(DetailViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> false
        }
    }

    private fun changeStatusBarColor() {
        val window = window
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorMainAlpha30)
    }
}