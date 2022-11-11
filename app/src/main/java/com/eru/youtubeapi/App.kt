package com.eru.youtubeapi

import android.app.Application
import com.eru.youtubeapi.data.remote.RemoteDataSource
import com.eru.youtubeapi.repository.Repository

class App: Application() {

    companion object{
        val repository: Repository by lazy {
            Repository()
        }

        val dataSource: RemoteDataSource by lazy {
            RemoteDataSource()
        }
    }
}