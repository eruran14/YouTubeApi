package com.eru.youtubeapi.remote

import com.eru.youtubeapi.BuildConfig.API_KEY
import com.eru.youtubeapi.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/youtube/v3/playlists")
    fun getPlaylists(
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("part") part: String
    ): Call<Playlists>
}