package com.zukron.jikanime.adapter.pagedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.listener.OnSelectedItemListener
import com.zukron.jikanime.model.DetailCharacterResponse
import com.zukron.jikanime.model.helper.AnimeMangaHelper
import kotlinx.android.synthetic.main.item_animeography.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/15/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class AnimeographyPagedListAdapter
    : PagedListAdapter<DetailCharacterResponse.Animeography, AnimeographyPagedListAdapter.ViewHolder>(AnimeographyDiffCallback()) {

    private var onSelectedItemListener: OnSelectedItemListener? = null
    private var selectedPos = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_animeography, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    private class AnimeographyDiffCallback : DiffUtil.ItemCallback<DetailCharacterResponse.Animeography>() {
        override fun areItemsTheSame(oldItem: DetailCharacterResponse.Animeography, newItem: DetailCharacterResponse.Animeography): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: DetailCharacterResponse.Animeography, newItem: DetailCharacterResponse.Animeography): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(animeography: DetailCharacterResponse.Animeography?) {
            itemView.animeographyItem_tvName.text = animeography?.name
            itemView.animeographyItem_tvRole.text = animeography?.role

            Glide.with(itemView.context)
                    .load(animeography?.imageUrl)
                    .placeholder(R.drawable.icons8_no_image_100)
                    .into(itemView.animeographyItem_imageView)

            itemView.animeographyItem_btnDetail.setOnClickListener {
                onSelectedItemListener?.onDetailIdItem(animeography?.malId, AnimeMangaHelper.ANIME)
            }

            itemView.animeographyItem_btnMyanimelist.setOnClickListener {
                onSelectedItemListener?.onDetailUrlItem(animeography?.url)
            }

            itemView.animeographyItem_btnBack.setOnClickListener {
                itemView.animeographyItem_flSelected.visibility = View.GONE
            }

            itemView.setOnClickListener {
                notifyItemChanged(selectedPos)
                selectedPos = super.getAdapterPosition()
                notifyItemChanged(selectedPos)
            }

            if (selectedPos == super.getAdapterPosition()) {
                itemView.animeographyItem_flSelected.visibility = View.VISIBLE
            } else {
                itemView.animeographyItem_flSelected.visibility = View.GONE
            }
        }
    }

    fun setOnPagedItemListener(onSelectedItemListener: OnSelectedItemListener) {
        this.onSelectedItemListener = onSelectedItemListener
    }
}