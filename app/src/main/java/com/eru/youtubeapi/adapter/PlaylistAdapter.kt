package com.eru.youtubeapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eru.youtubeapi.databinding.ItemPlaylistBinding
import com.eru.youtubeapi.extensions.load
import com.eru.youtubeapi.model.Item

class PlaylistAdapter(private var list: ArrayList<Item>, private val onClick: (pos: Int) -> Unit): RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

//    private val onClick: (pos: Int) -> Unit
    companion object{
        var id = ""
    }

    inner class ViewHolder(private val binding: ItemPlaylistBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(position: Int) {
            val imageUrl = list[position].snippet.thumbnails.medium.url
            val playlistTitle = list[position].snippet.title
            val numberOfVideos = list[position].contentDetails.itemCount
            val displayNumber = "$numberOfVideos video series"

            binding.ivPlaylist.load(imageUrl)
            binding.tvDescription.text = playlistTitle
            binding.tvVideosInPl.text = displayNumber

            binding.root.setOnClickListener {
                id = list[position].id
                onClick(adapterPosition)
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

