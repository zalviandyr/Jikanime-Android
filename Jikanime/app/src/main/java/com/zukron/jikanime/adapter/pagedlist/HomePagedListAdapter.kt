package com.zukron.jikanime.adapter.pagedlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.listener.OnSelectedItemListener
import com.zukron.jikanime.model.*
import com.zukron.jikanime.model.helper.AnimeMangaHelper
import com.zukron.jikanime.network.NetworkState
import com.zukron.jikanime.service.Utilities
import kotlinx.android.synthetic.main.item_anime_manga.view.*
import kotlinx.android.synthetic.main.item_loading.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/18/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class HomePagedListAdapter<T>(private val onSelectedItemListener: OnSelectedItemListener)
    : PagedListAdapter<T, RecyclerView.ViewHolder>(ItemDiffCallback<T>()) {
    val CONTENT_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null
    private var selectedPos: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View

        return if (viewType == CONTENT_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.item_anime_manga, parent, false)
            ContentViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.item_loading, parent, false)
            NetworkViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == CONTENT_VIEW_TYPE) {
            (holder as HomePagedListAdapter<*>.ContentViewHolder).bindTo(getItem(position))
        } else {
            (holder as HomePagedListAdapter<*>.NetworkViewHolder).bindTo(networkState)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            CONTENT_VIEW_TYPE
        }
    }

    private class ItemDiffCallback<R> : DiffUtil.ItemCallback<R>() {
        override fun areItemsTheSame(oldItem: R, newItem: R): Boolean {
            return if (oldItem is TopAnimeResponse.TopAnimeItem && newItem is TopAnimeResponse.TopAnimeItem) {
                oldItem.malId == newItem.malId
            } else if (oldItem is SeasonalAnimeResponse.SeasonalAnimeItem && newItem is SeasonalAnimeResponse.SeasonalAnimeItem) {
                oldItem.malId == newItem.malId
            } else if (oldItem is GenreAnimeResponse.GenreAnimeItem && newItem is GenreAnimeResponse.GenreAnimeItem) {
                oldItem.malId == newItem.malId
            } else if (oldItem is TopMangaResponse.TopMangaItem && newItem is TopMangaResponse.TopMangaItem) {
                oldItem.malId == newItem.malId
            } else if (oldItem is GenreMangaResponse.GenreMangaItem && newItem is GenreMangaResponse.GenreMangaItem) {
                oldItem.malId == newItem.malId
            } else {
                false
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: R, newItem: R): Boolean {
            return oldItem == newItem
        }
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(item: Any?) {
            val animeMangaHelper: AnimeMangaHelper? = when (item) {
                is TopAnimeResponse.TopAnimeItem -> {
                    AnimeMangaHelper(
                            title = item.title, rank = item.rank.toString(), score = item.score.toString(),
                            episodesOrVolumes = item.episodes.toString(), type = item.type, date = Utilities.concatDate(item.startDate, item.endDate),
                            image = item.image, url = item.url, malId = item.malId
                    )
                }
                is SeasonalAnimeResponse.SeasonalAnimeItem -> {
                    AnimeMangaHelper(
                            title = item.title, rank = "0", score = item.score.toString(),
                            episodesOrVolumes = item.episodes.toString(), type = item.type, date = Utilities.formatDate(item.airingStart),
                            image = item.imageUrl, url = item.url, malId = item.malId
                    )
                }
                is GenreAnimeResponse.GenreAnimeItem -> {
                    AnimeMangaHelper(
                            title = item.title, rank = "0", score = item.score.toString(),
                            episodesOrVolumes = item.episodes.toString(), type = item.type, date = Utilities.formatDate(item.airingStart),
                            image = item.imageUrl, url = item.url, malId = item.malId
                    )
                }
                is TopMangaResponse.TopMangaItem -> {
                    AnimeMangaHelper(
                            title = item.title, rank = item.rank.toString(), score = item.score.toString(),
                            episodesOrVolumes = item.volumes.toString(), type = item.type, date = Utilities.concatDate(item.startDate, item.endDate),
                            image = item.imageUrl, url = item.url, malId = item.malId
                    )
                }
                is GenreMangaResponse.GenreMangaItem -> {
                    AnimeMangaHelper(
                            title = item.title, rank = "0", score = item.score.toString(),
                            episodesOrVolumes = item.volumes.toString(), type = item.type, date = Utilities.formatDate(item.publishingStart),
                            image = item.imageUrl, url = item.url, malId = item.malId
                    )
                }
                else -> null
            }

            /***
             * bind into view
             */
            if (
                    item is SeasonalAnimeResponse.SeasonalAnimeItem ||
                    item is GenreAnimeResponse.GenreAnimeItem ||
                    item is GenreMangaResponse.GenreMangaItem) {
                // set visibility rank text view
                for (i in 1..3) {
                    val gridChild = itemView.animeMangaItem_gridLayout.getChildAt(i)
                    gridChild.visibility = View.GONE
                }
            }

            if (item is TopMangaResponse.TopMangaItem || item is GenreMangaResponse.GenreMangaItem) {
                itemView.animeMangaItem_tvEpisodeOrVolumeTitle.text = itemView.context.getString(R.string.volumes)
            }

            animeMangaHelper?.let { helper ->
                itemView.animeMangaItem_tvTitle.text = helper.title
                itemView.animeMangaItem_tvRank.text = helper.rank
                itemView.animeMangaItem_tvScore.text = helper.score
                itemView.animeMangaItem_tvEpisodeOrVolume.text = helper.episodesOrVolumes
                itemView.animeMangaItem_tvType.text = helper.type
                itemView.animeMangaItem_tvDate.text = helper.date

                Glide.with(itemView.context)
                        .load(helper.image)
                        .placeholder(R.drawable.icons8_no_image_100)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(100, 141)
                        .into(itemView.animeMangaItem_imageView)

                itemView.animeMangaItem_btnBack.setOnClickListener {
                    itemView.animeMangaItem_flSelected.visibility = View.GONE
                }

                itemView.animeMangaItem_btnMyanimelist.setOnClickListener {
                    onSelectedItemListener.onDetailUrlItem(helper.url)
                }

                itemView.animeMangaItem_btnDetail.setOnClickListener {
                    val type: String = if (
                            item is TopAnimeResponse.TopAnimeItem ||
                            item is SeasonalAnimeResponse.SeasonalAnimeItem ||
                            item is GenreAnimeResponse.GenreAnimeItem
                    ) {
                        AnimeMangaHelper.ANIME
                    } else {
                        AnimeMangaHelper.MANGA
                    }

                    onSelectedItemListener.onDetailIdItem(helper.malId, type)
                }
            }

            itemView.setOnClickListener {
                notifyItemChanged(selectedPos)
                selectedPos = super.getAdapterPosition()
                notifyItemChanged(selectedPos)
            }

            if (selectedPos == super.getAdapterPosition()) {
                itemView.animeMangaItem_flSelected.visibility = View.VISIBLE
            } else {
                itemView.animeMangaItem_flSelected.visibility = View.GONE
            }
        }
    }

    inner class NetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                notifyItemRemoved(super.getItemCount())             // remove progress bar
            } else {
                notifyItemInserted(super.getItemCount())            // add progress bar
            }
        } else if (hasExtraRow && previousNetwork != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}