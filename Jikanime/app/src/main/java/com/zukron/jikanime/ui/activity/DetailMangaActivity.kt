package com.zukron.jikanime.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.ViewPagerAdapter
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import com.zukron.jikanime.ui.fragment.detailmanga.DetailManga1Fragment
import com.zukron.jikanime.ui.fragment.detailmanga.DetailManga2Fragment
import com.zukron.jikanime.ui.fragment.detailmanga.DetailManga3Fragment
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class DetailMangaActivity : AppCompatActivity() {
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
        detailAct_materialToolbar.title = getString(R.string.detail_manga)
        setSupportActionBar(detailAct_materialToolbar)

        // view model
        val id = intent.getIntExtra(EXTRA_ID, 0)
        detailViewModel = getViewModel()
        detailViewModel.setMalId(id)

        // view pager 2
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        val fragmentList = listOf(
                DetailManga1Fragment(),
                DetailManga2Fragment(),
                DetailManga3Fragment()
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