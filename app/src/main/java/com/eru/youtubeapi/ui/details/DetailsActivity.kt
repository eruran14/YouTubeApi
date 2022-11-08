package com.eru.youtubeapi.ui.details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eru.youtubeapi.R
import com.eru.youtubeapi.core.common.Constant
import com.eru.youtubeapi.core.remote.result.Status
import com.eru.youtubeapi.core.ui.BaseActivity
import com.eru.youtubeapi.data.remote.model.ItemItem
import com.eru.youtubeapi.databinding.ActivityDetailsBinding
import com.eru.youtubeapi.ui.details.adapter.DetailsAdapter

class DetailsActivity : BaseActivity<DetailsViewModel, ActivityDetailsBinding>() {
    private lateinit var adapter: DetailsAdapter
    private var list = arrayListOf<ItemItem>()
    private lateinit var playlistId: String

    override val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }
    override fun inflateViewBinding(): ActivityDetailsBinding {
        return ActivityDetailsBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        val title = intent.getStringExtra(Constant.KEY_FOR_TITLE)
        val description = intent.getStringExtra(Constant.KEY_FOR_DESCRIPTION)
        val numberOfVideos = intent.getIntExtra(Constant.KEY_FOR_COUNT, 0)

        binding.tvTitle.text = title
        binding.tvDesc.text = description
        binding.videosNumber.text = String.format(getString(R.string.video_series), numberOfVideos)

        adapter = DetailsAdapter(list)
        binding.rvDetails.layoutManager = LinearLayoutManager(this)
        binding.rvDetails.adapter = adapter

        binding.tvBack.setOnClickListener {
            finish()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initViewModel() {
        super.initViewModel()

        viewModel.loading.observe(this){
            binding.progressBarDetails.isVisible = it
        }

        playlistId = intent.getStringExtra(Constant.KEY_FOR_ID).toString()

        viewModel.getPlaylistItems(playlistId).observe(this){ resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.items?.forEach { items ->
                        list.add(items)
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
            adapter.notifyDataSetChanged()
        }
    }
}