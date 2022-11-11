package com.eru.youtubeapi.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.eru.youtubeapi.databinding.ItemPlaylistBinding
import com.eru.youtubeapi.core.extensions.load
import com.eru.youtubeapi.data.remote.model.ItemItem

class DetailsAdapter(private var list: ArrayList<ItemItem>,
                     private val onClick: (videoId: String) -> Unit): RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPlaylistBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(position: Int) {
            val imageUrl = list[position].snippet.thumbnails.medium.url
            val playlistTitle = list[position].snippet.title
            val dateCreated = list[position].snippet.publishedAt


            binding.ivPlaylist.load(imageUrl)
            binding.tvDescription.text = playlistTitle
            binding.tvVideosInPl.text = dateCreated
            binding.textView.isVisible = false

            binding.root.setOnClickListener {
                onClick(list[position].snippet.resourceId.videoId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = list.size
}

