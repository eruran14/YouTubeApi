package com.eru.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import com.eru.youtubeapi.App.Companion.repository
import com.eru.youtubeapi.core.remote.result.Resource
import com.eru.youtubeapi.core.ui.BaseViewModel
import com.eru.youtubeapi.data.remote.model.Video

class VideoViewModel: BaseViewModel() {

    fun getVideo(videoId: String): LiveData<Resource<Video>> {
        return repository.getVideo(videoId)
    }
}