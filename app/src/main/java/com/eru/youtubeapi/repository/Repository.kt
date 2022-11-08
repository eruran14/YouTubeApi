package com.eru.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.eru.youtubeapi.BuildConfig
import com.eru.youtubeapi.core.common.Constant
import com.eru.youtubeapi.core.remote.RetrofitClient
import com.eru.youtubeapi.core.remote.result.Resource
import com.eru.youtubeapi.data.remote.ApiService
import com.eru.youtubeapi.data.remote.model.PlaylistItem
import com.eru.youtubeapi.data.remote.model.Playlists
import kotlinx.coroutines.Dispatchers

class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylists(): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = apiService.getPlaylists(Constant.channelId, BuildConfig.API_KEY, Constant.part, 50)
        emit(
            if (response.isSuccessful){
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), response.body(), response.code())
            }
        )
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItem>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = apiService.getPlaylistItems(playlistId, BuildConfig.API_KEY, Constant.part, 50)
        emit(
            if (response.isSuccessful){
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), response.body(), response.code())
            }
        )
    }
}