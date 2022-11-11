package com.eru.youtubeapi.data.remote

import com.eru.youtubeapi.BuildConfig
import com.eru.youtubeapi.core.common.Constant
import com.eru.youtubeapi.core.remote.BaseDataSource
import com.eru.youtubeapi.core.remote.RetrofitClient

class RemoteDataSource: BaseDataSource() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    suspend fun getPlaylists() = getResult {
        apiService.getPlaylists(Constant.channelId, BuildConfig.API_KEY, Constant.part, 50)
    }

    suspend fun getPlaylistItems(playlistId: String) = getResult {
        apiService.getPlaylistItems(playlistId, BuildConfig.API_KEY, Constant.part, 50)
    }

    suspend fun getVideo(videoId: String) = getResult {
        apiService.getVideo(BuildConfig.API_KEY, Constant.partVideo, videoId)
    }
}