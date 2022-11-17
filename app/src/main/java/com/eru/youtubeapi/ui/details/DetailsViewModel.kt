package com.eru.youtubeapi.ui.details

import androidx.lifecycle.LiveData
import com.eru.youtubeapi.core.remote.result.Resource
import com.eru.youtubeapi.core.ui.BaseViewModel
import com.eru.youtubeapi.data.remote.model.PlaylistItem
import com.eru.youtubeapi.repository.Repository

class DetailsViewModel(private val repository: Repository): BaseViewModel() {
    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItem>> {
        return repository.getPlaylistItems(playlistId)
    }
}