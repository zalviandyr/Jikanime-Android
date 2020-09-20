package com.zukron.jikanime.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zukron.jikanime.R
import kotlinx.android.synthetic.main.item_song.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    var dataList: List<String> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(data: String) {
            itemView.songItem_tvTitle.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}