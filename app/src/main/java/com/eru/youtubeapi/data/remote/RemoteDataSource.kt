package com.eru.youtubeapi.data.remote

import com.eru.youtubeapi.BuildConfig
import com.eru.youtubeapi.core.common.Constant
import com.eru.youtubeapi.core.remote.BaseDataSource
import org.koin.dsl.module

val dataSourceModules = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val apiService: ApiService): BaseDataSource() {

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