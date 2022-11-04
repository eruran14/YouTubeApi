package com.eru.youtubeapi.ui.details

import androidx.lifecycle.LiveData
import com.eru.youtubeapi.App
import com.eru.youtubeapi.core.ui.BaseViewModel
import com.eru.youtubeapi.data.remote.model.Playlists

class DetailsViewModel: BaseViewModel() {

    fun playlists(): LiveData<Playlists> {
        return App.repository.getPlaylists()
    }
}