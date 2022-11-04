package com.eru.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eru.youtubeapi.BuildConfig
import com.eru.youtubeapi.core.common.Constant
import com.eru.youtubeapi.core.remote.RetrofitClient
import com.eru.youtubeapi.data.remote.ApiService
import com.eru.youtubeapi.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylists(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()

        apiService.getPlaylists(Constant.channelId, BuildConfig.API_KEY, Constant.part, 50)
            .enqueue(object : Callback<Playlists> {

                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }
                override fun onFailure(call: Call<Playlists>, t: Throwable) {

                }
            })

        return data
    }
}