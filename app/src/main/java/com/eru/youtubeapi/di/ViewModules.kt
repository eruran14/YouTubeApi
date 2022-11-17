package com.eru.youtubeapi.di

import com.eru.youtubeapi.ui.details.DetailsViewModel
import com.eru.youtubeapi.ui.playlists.PlaylistsViewModel
import com.eru.youtubeapi.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlaylistsViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { VideoViewModel(get()) }
}