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
import com.zukron.jikanime.model.CharacterAnimeResponse.Character
import com.zukron.jikanime.network.NetworkState
import kotlinx.android.synthetic.main.item_character.view.*
import kotlinx.android.synthetic.main.item_loading.view.*

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/14/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class CharacterAnimePagedListAdapter
    : PagedListAdapter<Character, RecyclerView.ViewHolder>(CharacterDiffCallBack) {

    val CHARACTER_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null
    private var selectedPos: Int = RecyclerView.NO_POSITION
    private var onSelectedItemListener: OnSelectedItemListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        return if (viewType == CHARACTER_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.item_character, parent, false)
            CharacterViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.item_loading, parent, false)
            NetworkViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == CHARACTER_VIEW_TYPE) {
            (holder as CharacterViewHolder).bindTo(getItem(position))
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
            CHARACTER_VIEW_TYPE
        }
    }

    private object CharacterDiffCallBack : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(character: Character?) {
            if (character != null) {
                itemView.characterItem_tvName.text = character.name
                itemView.characterItem_tvRole.text = character.role

                val stringBuilder = StringBuilder()
                for (i in character.voiceActors.indices) {
                    stringBuilder.append(character.voiceActors[i].name)

                    if (i < (character.voiceActors.size - 1)) {
                        stringBuilder.append(" | ")
                    }
                }
                itemView.characterItem_tvVoiceActor.text = stringBuilder.toString()

                Glide.with(itemView.context)
                        .load(character.imageUrl)
                        .placeholder(R.drawable.icons8_no_image_100)
                        .into(itemView.characterItem_imageView)

                itemView.characterItem_btnBack.setOnClickListener {
                    itemView.characterItem_flSelected.visibility = View.GONE
                }

                itemView.characterItem_btnDetail.setOnClickListener {
                    onSelectedItemListener?.onDetailIdItem(character.malId, "")
                }

                itemView.characterItem_btnMyanimelist.setOnClickListener {
                    onSelectedItemListener?.onDetailUrlItem(character.url)
                }

                itemView.setOnClickListener {
                    notifyItemChanged(selectedPos)
                    selectedPos = super.getAdapterPosition()
                    notifyItemChanged(selectedPos)
                }

                if (selectedPos == super.getAdapterPosition()) {
                    itemView.characterItem_flSelected.visibility = View.VISIBLE
                } else {
                    itemView.characterItem_flSelected.visibility = View.GONE
                }
            }
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

    fun setOnPageItemListener(onSelectedItemListener: OnSelectedItemListener) {
        this.onSelectedItemListener = onSelectedItemListener
    }
}
