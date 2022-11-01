package com.eru.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.eru.youtubeapi.CheckNetworkState
import com.eru.youtubeapi.`object`.Constant
import com.eru.youtubeapi.adapter.PlaylistAdapter
import com.eru.youtubeapi.adapter.PlaylistAdapter.Companion.id
import com.eru.youtubeapi.base.BaseActivity
import com.eru.youtubeapi.base.BaseViewModel
import com.eru.youtubeapi.databinding.ActivityMainBinding
import com.eru.youtubeapi.model.Item
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

        adapter = PlaylistAdapter(list){
            startDetailsActivity(id)
        }

        viewModel.playlists().observe(this){ playlists ->
            playlists.items.forEach { item ->
                list.add(item)
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun initViews() {
        super.initViews()

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

    private fun startDetailsActivity(id: String) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(Constant.KEY_FOR_INTENT, id)
        startActivity(intent)
    }
}