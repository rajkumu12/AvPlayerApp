package com.example.core_module.repository

import com.example.core_module.model.AudioFolder
import com.example.core_module.model.AudioItem
import com.example.core_module.model.VideoFolder
import com.example.core_module.model.VideoItem

interface MediaRepository {

    suspend fun getVideoFolders(): List<VideoFolder>

    suspend fun getVideos(
        folderPath: String
    ): List<VideoItem>

    suspend fun getAudioFolders(): List<AudioFolder>
    suspend fun getAudios(
        folderPath: String
    ): List<AudioItem>
}