package com.eru.youtubeapi.ui.playlists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eru.youtubeapi.R
import com.eru.youtubeapi.databinding.ItemPlaylistBinding
import com.eru.youtubeapi.core.extensions.load
import com.eru.youtubeapi.data.remote.model.Item

class PlaylistAdapter(private var list: ArrayList<Item>,
                      private val onClick: (title: String, description: String, count: Int, playlistId: String) -> Unit)
    : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPlaylistBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(position: Int) {
            val imageUrl = list[position].snippet.thumbnails.medium.url
            val playlistTitle = list[position].snippet.title
            val numberOfVideos = list[position].contentDetails.itemCount
            val displayNumber = String.format(itemView.context.getString(R.string.video_series), numberOfVideos)

            binding.ivPlaylist.load(imageUrl)
            binding.tvDescription.text = playlistTitle
            binding.tvVideosInPl.text = displayNumber

            binding.root.setOnClickListener {
                onClick(playlistTitle, list[position].snippet.description, numberOfVideos, list[position].id)
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

