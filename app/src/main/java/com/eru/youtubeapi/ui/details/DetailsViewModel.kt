package com.eru.youtubeapi.ui.details

import androidx.lifecycle.LiveData
import com.eru.youtubeapi.App
import com.eru.youtubeapi.App.Companion.repository
import com.eru.youtubeapi.core.remote.result.Resource
import com.eru.youtubeapi.core.ui.BaseViewModel
import com.eru.youtubeapi.data.remote.model.PlaylistItem
import com.eru.youtubeapi.data.remote.model.Playlists

class DetailsViewModel: BaseViewModel() {
    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItem>> {
        return repository.getPlaylistItems(playlistId)
    }
}