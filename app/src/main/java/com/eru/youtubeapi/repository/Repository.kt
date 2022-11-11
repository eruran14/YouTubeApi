package com.eru.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.eru.youtubeapi.App.Companion.dataSource
import com.eru.youtubeapi.core.remote.result.Resource
import com.eru.youtubeapi.data.remote.model.PlaylistItem
import com.eru.youtubeapi.data.remote.model.Playlists
import com.eru.youtubeapi.data.remote.model.Video
import com.eru.youtubeapi.data.remote.model.VideoItem
import kotlinx.coroutines.Dispatchers

class Repository {


    fun getPlaylists(): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = dataSource.getPlaylists()
        emit(response)
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItem>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = dataSource.getPlaylistItems(playlistId)
        emit(response)
    }

    fun getVideo(videoId: String): LiveData<Resource<Video>> = liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = dataSource.getVideo(videoId)
        emit(response)
    }
}