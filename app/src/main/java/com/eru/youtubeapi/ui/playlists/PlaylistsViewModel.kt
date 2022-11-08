package com.eru.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import com.eru.youtubeapi.App.Companion.repository
import com.eru.youtubeapi.core.ui.BaseViewModel
import com.eru.youtubeapi.data.remote.model.Playlists
import com.eru.youtubeapi.core.remote.result.Resource

class PlaylistsViewModel : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return repository.getPlaylists()
    }


}