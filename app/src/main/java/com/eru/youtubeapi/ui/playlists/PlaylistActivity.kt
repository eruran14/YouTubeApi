package com.eru.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eru.youtubeapi.core.common.CheckNetworkState
import com.eru.youtubeapi.core.common.Constant
import com.eru.youtubeapi.core.remote.result.Status
import com.eru.youtubeapi.ui.playlists.adapter.PlaylistAdapter
import com.eru.youtubeapi.core.ui.BaseActivity
import com.eru.youtubeapi.core.ui.BaseViewModel
import com.eru.youtubeapi.databinding.ActivityMainBinding
import com.eru.youtubeapi.data.remote.model.Item
import com.eru.youtubeapi.ui.details.DetailsActivity

class PlaylistActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {
    private var list = arrayListOf<Item>()
    private lateinit var adapter: PlaylistAdapter
    private lateinit var checkNetworkState: CheckNetworkState

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this) [PlaylistsViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initViewModel() {
        super.initViewModel()

        adapter = PlaylistAdapter(list){ title, description, count, id ->
            startDetailsActivity(title, description, count, id)
        }

        viewModel.loading.observe(this){
            binding.progressBar.isVisible = it
        }

        viewModel.getPlaylists().observe(this){ resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.items?.forEach { item ->
                        list.add(item)
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

    override fun initViews() {
        super.initViews()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun isConnected() {
        super.isConnected()

        checkNetworkState = CheckNetworkState(application)
        checkNetworkState.observe(this) { isConnected ->
            if (isConnected) {
                binding.lNoInternet.root.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            } else {
                binding.lNoInternet.root.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.INVISIBLE
            }
        }
    }

    private fun startDetailsActivity(title: String, description: String, count: Int, playlistId: String) {
        Intent(this, DetailsActivity::class.java).apply {
            putExtra(Constant.KEY_FOR_TITLE, title)
            putExtra(Constant.KEY_FOR_DESCRIPTION, description)
            putExtra(Constant.KEY_FOR_COUNT, count)
            putExtra(Constant.KEY_FOR_ID, playlistId)
            startActivity(this)
        }
    }
}