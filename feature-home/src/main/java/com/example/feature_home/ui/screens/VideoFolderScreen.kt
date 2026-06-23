package com.example.feature_home.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_module.model.VideoItem
import com.example.feature_home.ui.components.VideoItemCard
import com.example.feature_home.ui.viewModels.VideoFolderViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun VideoFolderScreen(
    folderPath: String,
    onVideoClick: (VideoItem) -> Unit,
    viewModel: VideoFolderViewModel =
        hiltViewModel()
) {

    val videos by
    viewModel.videos.collectAsState()

    LaunchedEffect(folderPath) {

        viewModel.loadVideos(folderPath)
    }

    LazyColumn {

        items(videos) { video ->

            VideoItemCard(
                video = video,
                onClick = {
                    onVideoClick(video)
                }
            )
        }
    }
}