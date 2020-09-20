package com.zukron.jikanime.adapter.pagedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zukron.jikanime.R
import com.zukron.jikanime.model.DetailCharacterResponse
import kotlinx.android.synthetic.main.item_voice_actor.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/15/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class VoiceActorPagedListAdapter
    : PagedListAdapter<DetailCharacterResponse.VoiceActor, VoiceActorPagedListAdapter.ViewHolder>(VoiceActorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_voice_actor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class VoiceActorDiffCallback : DiffUtil.ItemCallback<DetailCharacterResponse.VoiceActor>() {
        override fun areItemsTheSame(oldItem: DetailCharacterResponse.VoiceActor, newItem: DetailCharacterResponse.VoiceActor): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: DetailCharacterResponse.VoiceActor, newItem: DetailCharacterResponse.VoiceActor): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(voiceActor: DetailCharacterResponse.VoiceActor?) {
            itemView.voiceActorItem_tvNo.text = (adapterPosition + 1).toString()
            itemView.voiceActorItem_tvName.text = voiceActor?.name
            itemView.voiceActorItem_tvLanguage.text = voiceActor?.language
        }
    }
}