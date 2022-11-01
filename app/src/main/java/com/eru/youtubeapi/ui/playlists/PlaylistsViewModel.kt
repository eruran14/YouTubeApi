package com.eru.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eru.youtubeapi.BuildConfig.API_KEY
import com.eru.youtubeapi.`object`.Constant
import com.eru.youtubeapi.base.BaseViewModel
import com.eru.youtubeapi.model.Playlists
import com.eru.youtubeapi.remote.ApiService
import com.eru.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<Playlists> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()

        apiService.getPlaylists(Constant.channelId, API_KEY, Constant.part)
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