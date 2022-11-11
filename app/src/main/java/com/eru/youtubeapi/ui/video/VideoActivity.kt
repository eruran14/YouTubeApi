package com.eru.youtubeapi.ui.video
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.eru.youtubeapi.core.common.Constant.KEY_FOR_VIDEO_ID
import com.eru.youtubeapi.core.extensions.load
import com.eru.youtubeapi.core.remote.result.Status
import com.eru.youtubeapi.core.ui.BaseActivity
import com.eru.youtubeapi.databinding.ActivityVideoBinding

class VideoActivity : BaseActivity<VideoViewModel, ActivityVideoBinding>() {
    override val viewModel: VideoViewModel by lazy {
        ViewModelProvider(this)[VideoViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()

        val videoId = intent.getStringExtra(KEY_FOR_VIDEO_ID)

        viewModel.getVideo(videoId.toString()).observe(this){ resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.items?.forEach {
                        binding.tvVideoTitle.text = it.snippet.title
                        binding.videoDescription.text = it.snippet.description
                        binding.videoPreview.load(it.snippet.thumbnails.high.url)
                    }
                    viewModel.loading.postValue(false)
                }
                Status.ERROR -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
            }
        }

        viewModel.loading.observe(this){
            binding.progressBarVideo.isVisible = it
        }
    }

    override fun initViews() {
        super.initViews()

        binding.tvBack.setOnClickListener {
            finish()
        }

        binding.videoDescription.movementMethod = ScrollingMovementMethod()
    }
}