package com.zukron.jikanime.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zukron.jikanime.R
import com.zukron.jikanime.model.DetailAnimeResponse
import com.zukron.jikanime.model.DetailMangaResponse
import kotlinx.android.synthetic.main.item_chip.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/13/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class ChipAdapter : RecyclerView.Adapter<ChipAdapter.ViewHolder>() {
    var dataList = listOf<Any>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(data: Any) {
            // detail anime
            if (data is DetailAnimeResponse.Genre) itemView.chipItem.text = data.name
            if (data is DetailAnimeResponse.Producer) itemView.chipItem.text = data.name
            if (data is DetailAnimeResponse.Studio) itemView.chipItem.text = data.name
            if (data is DetailAnimeResponse.Licensor) itemView.chipItem.text = data.name

            // detail manga
            if (data is DetailMangaResponse.Genre) itemView.chipItem.text = data.name
            if (data is DetailMangaResponse.Author) itemView.chipItem.text = data.name
            if (data is DetailMangaResponse.Serialization) itemView.chipItem.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_chip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}