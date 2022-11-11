package com.eru.youtubeapi.data.remote.model

data class Video(
    val etag: String,
    val items: List<VideoItem>,
    val kind: String,
    val pageInfo: VideoPageInfo
)

data class VideoDefault(
    val height: Int,
    val url: String,
    val width: Int
)

data class VideoHigh(
    val height: Int,
    val url: String,
    val width: Int
)

data class VideoItem(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: VideoSnippet
)

data class VideoLocalized(
    val description: String,
    val title: String
)

data class VideoMaxres(
    val height: Int,
    val url: String,
    val width: Int
)

data class VideoMedium(
    val height: Int,
    val url: String,
    val width: Int
)

data class VideoPageInfo(
    val resultsPerPage: Int,
    val totalResults: Int
)

data class VideoSnippet(
    val categoryId: String,
    val channelId: String,
    val channelTitle: String,
    val defaultAudioLanguage: String,
    val description: String,
    val liveBroadcastContent: String,
    val localized: VideoLocalized,
    val publishedAt: String,
    val tags: List<String>,
    val thumbnails: VideoThumbnails,
    val title: String
)

data class VideoStandard(
    val height: Int,
    val url: String,
    val width: Int
)

data class VideoThumbnails(
    val default: VideoDefault,
    val high: VideoHigh,
    val maxres: VideoMaxres,
    val medium: VideoMedium,
    val standard: VideoStandard
)