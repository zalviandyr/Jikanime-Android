package com.zukron.jikanime.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.listener.OnSelectedItemListener
import com.zukron.jikanime.model.Favorite
import kotlinx.android.synthetic.main.item_favorite_anime_manga.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/20/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class FavoriteAdapter(private val onSelectedItemListener: OnSelectedItemListener)
    : ListAdapter<Favorite, FavoriteAdapter.ViewHolder>(FavoriteCallBack()) {

    private var selectedPos = RecyclerView.NO_POSITION

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(item: Favorite?) {
            item?.let {
                itemView.animeMangaFavoriteItem_tvTitle.text = it.title
                itemView.animeMangaFavoriteItem_tvScore.text = it.score

                Glide.with(itemView.context)
                        .load(it.imageUrl)
                        .placeholder(R.drawable.icons8_no_image_100)
                        .into(itemView.animeMangaFavoriteItem_imageView)

                itemView.animeMangaFavoriteItem_btnBack.setOnClickListener {
                    itemView.animeMangaFavoriteItem_flSelected.visibility = View.GONE
                }

                itemView.animeMangaFavoriteItem_btnMyanimelist.setOnClickListener { _ ->
                    onSelectedItemListener.onDetailUrlItem(it.url)
                }

                itemView.animeMangaFavoriteItem_btnDetail.setOnClickListener { _ ->
                    if (it.type == Favorite.ANIME) {
                        onSelectedItemListener.onDetailIdItem(it.malId, Favorite.ANIME)
                    }

                    if (it.type == Favorite.MANGA) {
                        onSelectedItemListener.onDetailIdItem(it.malId, Favorite.MANGA)
                    }
                }

                itemView.setOnClickListener {
                    notifyItemChanged(selectedPos)
                    selectedPos = super.getAdapterPosition()
                    notifyItemChanged(selectedPos)
                }

                if (selectedPos == super.getAdapterPosition()) {
                    itemView.animeMangaFavoriteItem_flSelected.visibility = View.VISIBLE
                } else {
                    itemView.animeMangaFavoriteItem_flSelected.visibility = View.GONE
                }
            }
        }
    }

    private class FavoriteCallBack : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favorite_anime_manga, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}
