package com.eru.youtubeapi

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

class CheckNetworkState (private val connectivityManager: ConnectivityManager) : LiveData<Boolean>(){

    constructor(application: Application): this(application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    private val networkCallback = @RequiresApi(VERSION_CODES.P)

    object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    @RequiresApi(VERSION_CODES.P)
    override fun onActive() {
        super.onActive()

        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    @RequiresApi(VERSION_CODES.P)
    override fun onInactive() {
        super.onInactive()

        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}