package com.example.feature_home.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_home.ui.components.AudioItemCard
import com.example.feature_home.ui.viewModels.AudioFolderViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AudioFolderScreen(
    folderPath: String,
    viewModel: AudioFolderViewModel =
        hiltViewModel()
) {

    val audios by
    viewModel.audios.collectAsState()

    Log.d("iiijfjfjffj", "AudioFolderScreen: $audios ")

    LaunchedEffect(folderPath) {

        viewModel.loadAudios(folderPath)
    }

    LazyColumn {

        items(audios) { audio ->

            AudioItemCard(
                audio = audio,
                onClick = {

                }
            )
        }
    }
}