package com.mobifyall.mvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobifyall.mvvm.R
import com.mobifyall.mvvm.databinding.EpisodeItemBinding
import com.mobifyall.mvvm.models.Episode
import com.mobifyall.mvvm.viewstates.EpisodeItemViewState

class EpisodesNameAdapter(private val episodes: List<Episode>?) :
        RecyclerView.Adapter<EpisodesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder.create(parent, R.layout.episode_item)
    }

    override fun getItemCount(): Int = episodes?.size ?: 0

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.binding.viewState = EpisodeItemViewState(episode = episodes?.get(position),
                number = (position + 1).toString())
    }
}


class EpisodesViewHolder(var binding: EpisodeItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup, @LayoutRes resource: Int): EpisodesViewHolder {
            val binding = DataBindingUtil.inflate<EpisodeItemBinding>(
                    LayoutInflater.from(parent.context), resource, parent, false)
            return EpisodesViewHolder(binding)
        }
    }
}