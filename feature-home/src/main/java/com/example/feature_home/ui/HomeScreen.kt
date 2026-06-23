package com.example.feature_home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.core_common.utils.HomeTab
import com.example.core_ui.DarkBackground
import com.example.core_ui.R
import com.example.feature_home.ui.screens.AudioScreen
import com.example.feature_home.ui.screens.DownloadsScreen
import com.example.feature_home.ui.screens.ProfileScreen
import com.example.feature_home.ui.screens.VideosScreen
import androidx.compose.material3.NavigationBar
import com.example.core_module.model.VideoItem
import com.example.core_ui.TextFieldBg

@Composable
fun HomeScreen(
    onVideoClick: (VideoItem) -> Unit
) {
    var selectedTab by rememberSaveable {
        mutableStateOf(HomeTab.VIDEOS)
    }
    Scaffold(
        containerColor=DarkBackground,
        bottomBar = {

            NavigationBar(containerColor = TextFieldBg) {

                NavigationBarItem(
                    selected = selectedTab == HomeTab.VIDEOS,
                    onClick = {
                        selectedTab = HomeTab.VIDEOS
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.outline_books_movies_and_music_24),
                            contentDescription = null
                        )
                    },
                    label = { Text("Videos") }
                )

                NavigationBarItem(
                    selected = selectedTab == HomeTab.AUDIO,
                    onClick = {
                        selectedTab = HomeTab.AUDIO
                    },
                    icon = {Icon(
                        painter = painterResource(R.drawable.baseline_music_note_24),
                        contentDescription = null
                    ) },
                    label = { Text("Audio") }
                )

                NavigationBarItem(
                    selected = selectedTab == HomeTab.DOWNLOADS,
                    onClick = {
                        selectedTab = HomeTab.DOWNLOADS
                    },
                    icon = { Icon(
                        painter = painterResource(R.drawable.rounded_bookmark_manager_24),
                        contentDescription = null
                    ) },
                    label = { Text("Downloads") }
                )

                NavigationBarItem(
                    selected = selectedTab == HomeTab.PROFILE,
                    onClick = {
                        selectedTab = HomeTab.PROFILE
                    },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profile") }
                )
            }
        }

    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding).background(color = DarkBackground)
        ) {

            when (selectedTab) {

                HomeTab.VIDEOS -> VideosScreen()


                HomeTab.AUDIO -> AudioScreen()

                HomeTab.DOWNLOADS -> DownloadsScreen(
                    onVideoClick = { video ->
                        onVideoClick(video)
                    }
                )

                HomeTab.PROFILE -> ProfileScreen()
            }
        }
    }
}