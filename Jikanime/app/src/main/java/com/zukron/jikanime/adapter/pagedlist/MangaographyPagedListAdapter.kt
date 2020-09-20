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
import kotlinx.android.synthetic.main.item_mangaography.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/15/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class MangaographyPagedListAdapter
    : PagedListAdapter<DetailCharacterResponse.Mangaography, MangaographyPagedListAdapter.ViewHolder>(MangaographyDiffCallback()) {

    private var onSelectedItemListener: OnSelectedItemListener? = null
    private var selectedPos = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_mangaography, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    private class MangaographyDiffCallback : DiffUtil.ItemCallback<DetailCharacterResponse.Mangaography>() {
        override fun areItemsTheSame(oldItem: DetailCharacterResponse.Mangaography, newItem: DetailCharacterResponse.Mangaography): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: DetailCharacterResponse.Mangaography, newItem: DetailCharacterResponse.Mangaography): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(mangaography: DetailCharacterResponse.Mangaography?) {
            itemView.mangaographyItem_tvName.text = mangaography?.name
            itemView.mangaographyItem_tvRole.text = mangaography?.role

            Glide.with(itemView.context)
                    .load(mangaography?.imageUrl)
                    .placeholder(R.drawable.icons8_no_image_100)
                    .into(itemView.mangaographyItem_imageView)

            itemView.mangaographyItem_btnMyanimelist.setOnClickListener {
                onSelectedItemListener?.onDetailUrlItem(mangaography?.url)
            }

            itemView.mangaographyItem_btnDetail.setOnClickListener {
                onSelectedItemListener?.onDetailIdItem(mangaography?.malId, AnimeMangaHelper.MANGA)
            }

            itemView.mangaographyItem_btnBack.setOnClickListener {
                itemView.mangaographyItem_flSelected.visibility = View.GONE
            }

            itemView.setOnClickListener {
                notifyItemChanged(selectedPos)
                selectedPos = super.getAdapterPosition()
                notifyItemChanged(selectedPos)
            }

            if (selectedPos == super.getAdapterPosition()) {
                itemView.mangaographyItem_flSelected.visibility = View.VISIBLE
            } else {
                itemView.mangaographyItem_flSelected.visibility = View.GONE
            }
        }
    }

    fun setOnPagedItemListener(onSelectedItemListener: OnSelectedItemListener) {
        this.onSelectedItemListener = onSelectedItemListener
    }
}