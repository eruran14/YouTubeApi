package com.eru.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eru.youtubeapi.App
import com.eru.youtubeapi.App.Companion.repository
import com.eru.youtubeapi.BuildConfig.API_KEY
import com.eru.youtubeapi.core.common.Constant
import com.eru.youtubeapi.core.ui.BaseViewModel
import com.eru.youtubeapi.data.remote.model.Playlists
import com.eru.youtubeapi.data.remote.ApiService
import com.eru.youtubeapi.core.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : BaseViewModel() {

    fun playlists(): LiveData<Playlists> {
        return repository.getPlaylists()
    }


}