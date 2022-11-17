package com.eru.youtubeapi

import android.app.Application
import com.eru.youtubeapi.data.remote.RemoteDataSource
import com.eru.youtubeapi.di.koinModules
import com.eru.youtubeapi.repository.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }
}