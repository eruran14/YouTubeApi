package com.eru.youtubeapi.di

import com.eru.youtubeapi.core.remote.networkModule
import com.eru.youtubeapi.data.remote.dataSourceModules

val koinModules = listOf(
    networkModule,
    repoModules,
    viewModules,
    dataSourceModules
)