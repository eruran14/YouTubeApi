package com.eru.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import com.eru.youtubeapi.core.ui.BaseViewModel
import com.eru.youtubeapi.data.remote.model.Playlists
import com.eru.youtubeapi.core.remote.result.Resource
import com.eru.youtubeapi.repository.Repository

class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return repository.getPlaylists()
    }


}