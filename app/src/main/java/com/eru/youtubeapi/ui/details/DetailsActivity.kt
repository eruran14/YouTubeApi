package com.eru.youtubeapi.ui.details

import android.annotation.SuppressLint
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eru.youtubeapi.R
import com.eru.youtubeapi.core.common.Constant
import com.eru.youtubeapi.core.ui.BaseActivity
import com.eru.youtubeapi.data.remote.model.Item
import com.eru.youtubeapi.databinding.ActivityDetailsBinding
import com.eru.youtubeapi.ui.details.adapter.DetailsAdapter

class DetailsActivity : BaseActivity<DetailsViewModel, ActivityDetailsBinding>() {
    private lateinit var adapter: DetailsAdapter
    private var list = arrayListOf<Item>()

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

        viewModel.playlists().observe(this){ playlists ->
            playlists.items.forEach {
                list.add(it)
            }
            adapter.notifyDataSetChanged()
        }
    }
}