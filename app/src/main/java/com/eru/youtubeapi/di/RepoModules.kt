package com.eru.youtubeapi.di

import com.eru.youtubeapi.repository.Repository
import org.koin.dsl.module

val repoModules = module {
    single { Repository(get()) }
}