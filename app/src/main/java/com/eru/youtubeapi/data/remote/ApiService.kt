package com.eru.youtubeapi.data.remote

import com.eru.youtubeapi.data.remote.model.PlaylistItem
import com.eru.youtubeapi.data.remote.model.Playlists
import com.eru.youtubeapi.data.remote.model.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/youtube/v3/playlists")
    suspend fun getPlaylists(
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int
    ): Response<Playlists>

    @GET("/youtube/v3/playlistItems")
    suspend fun getPlaylistItems(
        @Query("playlistId") channelId: String,
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int
    ): Response<PlaylistItem>

    @GET("/youtube/v3/videos")
    suspend fun getVideo(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("id") videoId: String
    ): Response<Video>


}