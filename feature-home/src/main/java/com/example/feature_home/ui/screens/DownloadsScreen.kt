package com.example.feature_home.ui.screens

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.core_common.utils.DownloadTab
import com.example.core_module.model.VideoItem
import com.example.core_ui.TextFieldBg
import com.example.feature_home.ui.components.FolderItem
import com.example.feature_home.ui.components.TabButton
import com.example.feature_home.ui.permissions.MediaPermission
import com.example.feature_home.ui.viewModels.DownloadsViewModel

@SuppressLint("NewApi")
@Composable
fun DownloadsScreen(
    onVideoClick: (VideoItem) -> Unit,
    viewModel: DownloadsViewModel = hiltViewModel()
) {
    var selectedTab by rememberSaveable {
        mutableStateOf(DownloadTab.VIDEOS)
    }
    var selectedVideoFolder by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    var selectedAudioFolder by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    val folders by viewModel.videoFolders.collectAsState()
    val audioFolders by viewModel.audioFolders.collectAsState()
    val context = LocalContext.current

    BackHandler(
        enabled = selectedVideoFolder != null || selectedAudioFolder != null
    ) {

        selectedVideoFolder = null
        selectedAudioFolder = null

    }
    val hasPermission = remember {

        mutableStateOf(

            MediaPermission.permissions.all { permission ->

                ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            hasPermission.value =
                permissions.values.all { it }
        }

    LaunchedEffect(Unit) {

        if (!hasPermission.value) {

            permissionLauncher.launch(
                MediaPermission.permissions
            )
        }
    }

    if (!hasPermission.value) {

        Text("Permission Required")

        return
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = TextFieldBg,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {

                TabButton(
                    title = "Videos",
                    selected = selectedTab == DownloadTab.VIDEOS,
                    modifier = Modifier.weight(1f)
                ) {
                    selectedTab = DownloadTab.VIDEOS
                }

                TabButton(
                    title = "Audio",
                    selected = selectedTab == DownloadTab.AUDIO,
                    modifier = Modifier.weight(1f)
                ) {
                    selectedTab = DownloadTab.AUDIO
                }
            }
        }

        if (selectedTab == DownloadTab.VIDEOS) {
            if (selectedVideoFolder == null) {
                LazyColumn {

                    items(folders) { folder ->

                        FolderItem(
                            name = folder.folderName,
                            count = folder.count,
                            onClick = {

                                selectedVideoFolder =
                                    folder.folderPath
                            }
                        )
                    }
                }
            }else{
                VideoFolderScreen(

                    folderPath = selectedVideoFolder!!,
                    onVideoClick = { video ->

                        onVideoClick(video)
                    }
                )
            }
        } else {

            if (selectedAudioFolder == null){
                LazyColumn {

                    items(audioFolders) { folder ->

                        FolderItem(
                            name = folder.folderName,
                            count = folder.count,
                            onClick = {

                                selectedAudioFolder =
                                    folder.folderPath
                            }
                        )
                    }
                }

            }else{
                AudioFolderScreen(
                    folderPath = selectedAudioFolder!!
                )
            }


        }
    }
}