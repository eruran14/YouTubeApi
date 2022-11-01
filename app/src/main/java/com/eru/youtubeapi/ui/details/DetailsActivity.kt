package com.eru.youtubeapi.ui.details

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.eru.youtubeapi.`object`.Constant
import com.eru.youtubeapi.base.BaseActivity
import com.eru.youtubeapi.base.BaseViewModel
import com.eru.youtubeapi.databinding.ActivityDetailsBinding

class DetailsActivity : BaseActivity<BaseViewModel, ActivityDetailsBinding>() {
    override val viewModel: BaseViewModel by lazy {
        ViewModelProvider(this)[BaseViewModel::class.java]
    }
    override fun inflateViewBinding(): ActivityDetailsBinding {
        return ActivityDetailsBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        val text = intent.getStringExtra(Constant.KEY_FOR_INTENT)

        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}