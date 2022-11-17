package com.eru.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import com.eru.youtubeapi.core.remote.result.Resource
import com.eru.youtubeapi.core.ui.BaseViewModel
import com.eru.youtubeapi.data.remote.model.Video
import com.eru.youtubeapi.repository.Repository

class VideoViewModel(private val repository: Repository): BaseViewModel() {

    fun getVideo(videoId: String): LiveData<Resource<Video>> {
        return repository.getVideo(videoId)
    }
}