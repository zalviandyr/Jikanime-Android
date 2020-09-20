package com.zukron.jikanime.adapter.pagedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zukron.jikanime.R
import com.zukron.jikanime.model.EpisodeAnimeResponse.Episode
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.service.Utilities
import kotlinx.android.synthetic.main.item_episode.view.*
import kotlinx.android.synthetic.main.item_loading.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/12/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class EpisodeAnimePagedListAdapter
    : PagedListAdapter<Episode, RecyclerView.ViewHolder>(EpisodeDiffCallback()) {

    val EPISODE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        return if (viewType == EPISODE_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.item_episode, parent, false)
            EpisodeViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.item_loading, parent, false)
            NetworkViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == EPISODE_VIEW_TYPE) {
            (holder as EpisodeViewHolder).bindTo(getItem(position))
        } else {
            (holder as NetworkViewHolder).bindTo(networkState)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            EPISODE_VIEW_TYPE
        }
    }

    private class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.episodeId == newItem.episodeId
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }
    }

    inner class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(episode: Episode?) {

            itemView.episodeItem_tvNo.text = episode?.episodeId.toString()
            itemView.episodeItem_tvTitle.text = episode?.title
            itemView.episodeItem_tvTitleRomanji.text = episode?.titleRomanji
            itemView.episodeItem_tvAired.text = Utilities.formatDate(episode?.aired)
        }
    }

    class NetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(networkState: NetworkState?) {
            if (networkState != null && networkState == NetworkState.LOADING) {
                itemView.loadingItem_progressBar.visibility = View.VISIBLE
            } else {
                itemView.loadingItem_progressBar.visibility = View.GONE
            }
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousNetwork = this.networkState
        val hadExtraRow = hasExtraRow()

        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousNetwork != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}