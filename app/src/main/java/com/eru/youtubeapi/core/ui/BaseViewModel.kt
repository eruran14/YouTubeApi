package com.eru.youtubeapi.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    var loading = MutableLiveData<Boolean>()
}