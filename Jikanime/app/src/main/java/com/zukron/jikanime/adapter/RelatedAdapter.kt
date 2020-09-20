package com.zukron.jikanime.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zukron.jikanime.R
import com.zukron.jikanime.adapter.listener.OnSelectedItemListener
import com.zukron.jikanime.model.DetailAnimeResponse
import com.zukron.jikanime.model.DetailMangaResponse
import kotlinx.android.synthetic.main.item_related.view.*
import java.lang.StringBuilder

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/13/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class RelatedAdapter : RecyclerView.Adapter<RelatedAdapter.ViewHolder>() {
    var dataList: List<Any> = listOf()
    private var selectedPos = RecyclerView.NO_POSITION
    private var onSelectedItemListener: OnSelectedItemListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(any: Any, position: Int) {
            var malId: Int? = null
            var type: String? = null
            var url: String? = null

            // detail anime
            if (any is DetailAnimeResponse.Related.Adaptation) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailAnimeResponse.Related.SideStory) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailAnimeResponse.Related.Summary) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailAnimeResponse.Related.Sequel) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailAnimeResponse.Related.Prequel) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailAnimeResponse.Related.SpinOff) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            // detail manga
            if (any is DetailMangaResponse.Related.Adaptation) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailMangaResponse.Related.SideStory) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailMangaResponse.Related.Summary) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailMangaResponse.Related.Sequel) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailMangaResponse.Related.Prequel) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            if (any is DetailAnimeResponse.Related.SpinOff) {
                itemView.relatedItem_tvNo.text = StringBuilder().append(position).append(".")
                itemView.relatedItem_tvTitle.text = any.name
                itemView.relatedItem_tvType.text = any.type

                malId = any.malId
                type = any.type
                url = any.url
            }

            itemView.relatedItem_btnDetail.setOnClickListener {
                onSelectedItemListener?.onDetailIdItem(malId, type!!)
            }

            itemView.relatedItem_btnMyanimelist.setOnClickListener {
                onSelectedItemListener?.onDetailUrlItem(url)
            }

            itemView.relatedItem_btnBack.setOnClickListener {
                itemView.relatedItem_flSelected.visibility = View.GONE
            }

            itemView.setOnClickListener {
                notifyItemChanged(selectedPos)
                selectedPos = super.getAdapterPosition()
                notifyItemChanged(selectedPos)
            }

            if (selectedPos == super.getAdapterPosition()) {
                itemView.relatedItem_flSelected.visibility = View.VISIBLE
            } else {
                itemView.relatedItem_flSelected.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_related, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(dataList[position], position + 1)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setOnSelectedItemListener(onSelectedItemListener: OnSelectedItemListener) {
        this.onSelectedItemListener = onSelectedItemListener
    }
}