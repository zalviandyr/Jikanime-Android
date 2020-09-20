package com.zukron.jikanime.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.ViewPagerAdapter
import com.zukron.jikanime.model.helper.HomeHelper
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.ui.activity.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/16/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // toolbar
        setSupportActionBar(mainAct_toolbar)
        mainAct_toolbar.setOnMenuItemClickListener(this)

        // nav view
        mainAct_navView.setNavigationItemSelectedListener(this)
        mainAct_navView.setCheckedItem(R.id.nav_top_anime)

        // view pager adapter
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        // view model
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.setHomeHelper(HomeHelper.TopAnime)

        homeViewModel.networkStateLiveData.observe(this) {
            if (it == NetworkState.NO_CONNECTION) {
                Snackbar.make(mainAct_drawerLayout, it.message, Snackbar.LENGTH_INDEFINITE)
                        .show()
            }
        }

        // set fragment
        homeViewModel.fragmentListLiveData.observe(this) {
            mainAct_viewPager2.adapter = null
            viewPagerAdapter.fragmentList = it
            mainAct_viewPager2.adapter = viewPagerAdapter
        }

        // set tab layout
        homeViewModel.titleListLiveData.observe(this) {
            val tabConfiguration = TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = it[position]
                mainAct_viewPager2.setCurrentItem(tab.position, true)
            }

            val tabLayoutMediator = TabLayoutMediator(
                    mainAct_tabLayout,
                    mainAct_viewPager2,
                    true,
                    true,
                    tabConfiguration
            )

            tabLayoutMediator.attach()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            mainAct_drawerLayout.openDrawer(GravityCompat.START)
            true
        } else {
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.nav_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }
            else -> false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // close drawer
        mainAct_drawerLayout.closeDrawer(GravityCompat.START)

        return when (item.itemId) {
            R.id.nav_top_anime -> {
                homeViewModel.setHomeHelper(HomeHelper.TopAnime)
                true
            }
            R.id.nav_spring_anime -> {
                homeViewModel.setHomeHelper(HomeHelper.SeasonalAnime.Spring)
                true
            }
            R.id.nav_summer_anime -> {
                homeViewModel.setHomeHelper(HomeHelper.SeasonalAnime.Summer)
                true
            }
            R.id.nav_fall_anime -> {
                homeViewModel.setHomeHelper(HomeHelper.SeasonalAnime.Fall)
                true
            }
            R.id.nav_winter_anime -> {
                homeViewModel.setHomeHelper(HomeHelper.SeasonalAnime.Winter)
                true
            }
            R.id.nav_genre_anime -> {
                homeViewModel.setHomeHelper(HomeHelper.GenreAnime)
                true
            }
            R.id.nav_top_manga -> {
                homeViewModel.setHomeHelper(HomeHelper.TopManga)
                true
            }
            R.id.nav_genre_manga -> {
                homeViewModel.setHomeHelper(HomeHelper.GenreManga)
                true
            }
            R.id.nav_favorite_other -> {
                homeViewModel.setHomeHelper(HomeHelper.Favorite)
                true
            }
            else -> false
        }
    }
}