package com.eru.youtubeapi.ui.video
import android.text.method.ScrollingMovementMethod
import android.util.SparseArray
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.eru.youtubeapi.core.common.Constant.KEY_FOR_VIDEO_ID
import com.eru.youtubeapi.core.remote.result.Status
import com.eru.youtubeapi.core.ui.BaseActivity
import com.eru.youtubeapi.databinding.ActivityVideoBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : BaseActivity<VideoViewModel, ActivityVideoBinding>() {
    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playBackPosition: Long = 0
    private lateinit var youTubeLink: String


    override val viewModel: VideoViewModel by viewModel()

    override fun inflateViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()

        val videoId = intent.getStringExtra(KEY_FOR_VIDEO_ID)

        youTubeLink = "https://www.youtube.com/watch?v=$videoId"

        viewModel.getVideo(videoId.toString()).observe(this){ resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.items?.forEach {
                        binding.tvVideoTitle.text = it.snippet.title
                        binding.videoDescription.text = it.snippet.description
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

        initPlayer()
    }

    private fun initPlayer(){
        player = ExoPlayer.Builder(this).build()
        binding.videoPlayer.player = player

        playYouTubeVideo(youTubeLink)
    }

    private fun playYouTubeVideo(link: String) = object : YouTubeExtractor(this){
        override fun onExtractionComplete(
            ytFiles: SparseArray<YtFile>?,
            videoMeta: VideoMeta?
        ) {
            if (ytFiles != null){
                val videoTag = 18
                val audioTag = 18
                val audioSource = ProgressiveMediaSource
                    .Factory(DefaultHttpDataSource.Factory())
                    .createMediaSource(MediaItem.fromUri(ytFiles.get(audioTag).url))

                val videoSource = ProgressiveMediaSource
                    .Factory(DefaultHttpDataSource.Factory())
                    .createMediaSource(MediaItem.fromUri(ytFiles.get(videoTag).url))
                player?.setMediaSource(MergingMediaSource(true, videoSource, audioSource), true)
                player?.prepare()
                player?.playWhenReady = playWhenReady
                player?.seekTo(currentWindow, playBackPosition)
            }
        }
    }.extract(link, false, true)

    override fun onStop() {
        super.onStop()

        releasePlayer()
    }

    private fun releasePlayer() {
        if(player != null){
            playWhenReady = player!!.playWhenReady
            playBackPosition = player!!.currentPosition
            currentWindow = player!!.currentMediaItemIndex
            player!!.release()
            player = null
        }
    }

    override fun onResume() {
        super.onResume()

        if (player == null){
            initPlayer()
        }
    }

    override fun onPause() {
        releasePlayer()
        super.onPause()
    }

}