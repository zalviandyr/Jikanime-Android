package com.zukron.jikanime.ui.fragment.detailmanga

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.RelatedAdapter
import com.zukron.jikanime.adapter.listener.OnSelectedItemListener
import com.zukron.jikanime.ui.activity.DetailAnimeActivity
import com.zukron.jikanime.ui.activity.DetailMangaActivity
import com.zukron.jikanime.ui.activity.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_related.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class DetailManga3Fragment : Fragment(), OnSelectedItemListener {
    private lateinit var detailViewModel: DetailViewModel
    private val animeType = "anime"
    private val mangaType = "manga"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_related, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view model
        detailViewModel = requireActivity().let {
            ViewModelProvider(it).get(DetailViewModel::class.java)
        }

        detailViewModel.detailManga.observe(requireActivity()) {
            Log.d("DetailManga", it.malId.toString())
            val related = it.related

            val adaptation = related.adaptation
            val sideStory = related.sideStory
            val summary = related.summary
            val sequel = related.sequel
            val prequel = related.prequel
            val spinOff = related.spinOff

            // adaptation
            if (adaptation == null) {
                detailRelatedFrag_adaptationContent.visibility = View.GONE
            } else {
                val adapter = RelatedAdapter()
                adapter.dataList = adaptation
                adapter.setOnSelectedItemListener(this)
                detailRelatedFrag_rvAdaptation.adapter = adapter
            }

            // side story
            if (sideStory == null) {
                detailRelatedFrag_sideStoryContent.visibility = View.GONE
            } else {
                val adapter = RelatedAdapter()
                adapter.dataList = sideStory
                adapter.setOnSelectedItemListener(this)
                detailRelatedFrag_rvSideStory.adapter = adapter
            }

            // summary
            if (summary == null) {
                detailRelatedFrag_summaryContent.visibility = View.GONE
            } else {
                val adapter = RelatedAdapter()
                adapter.dataList = summary
                adapter.setOnSelectedItemListener(this)
                detailRelatedFrag_rvSummary.adapter = adapter
            }

            // sequel
            if (sequel == null) {
                detailRelatedFrag_sequelContent.visibility = View.GONE
            } else {
                val adapter = RelatedAdapter()
                adapter.dataList = sequel
                adapter.setOnSelectedItemListener(this)
                detailRelatedFrag_rvSequel.adapter = adapter
            }

            // prequel
            if (prequel == null) {
                detailRelatedFrag_prequelContent.visibility = View.GONE
            } else {
                val adapter = RelatedAdapter()
                adapter.dataList = prequel
                adapter.setOnSelectedItemListener(this)
                detailRelatedFrag_rvPrequel.adapter = adapter
            }

            // spin off
            if (spinOff == null) {
                detailRelatedFrag_spinOffContent.visibility = View.GONE
            } else {
                val adapter = RelatedAdapter()
                adapter.dataList = spinOff
                adapter.setOnSelectedItemListener(this)
                detailRelatedFrag_rvSpinOff.adapter = adapter
            }
        }
    }

    override fun onDetailIdItem(id: Int?, type: String) {
        if (type == animeType) {
            val intent = Intent(context, DetailAnimeActivity::class.java)
            intent.putExtra(DetailAnimeActivity.EXTRA_ID, id)

            startActivity(intent)
        } else if (type == mangaType) {
            val intent = Intent(context, DetailMangaActivity::class.java)
            intent.putExtra(DetailMangaActivity.EXTRA_ID, id)

            startActivity(intent)
        }
    }

    override fun onDetailUrlItem(url: String?) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri

        startActivity(intent)
    }
}