package com.eru.youtubeapi

import android.app.Application
import com.eru.youtubeapi.repository.Repository

class App: Application() {

    companion object{
        val repository: Repository by lazy {
            Repository()
        }
    }
}