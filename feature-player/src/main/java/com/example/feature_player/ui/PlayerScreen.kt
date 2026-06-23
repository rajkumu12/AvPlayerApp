package com.example.feature_player.ui


import android.app.Activity
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@UnstableApi
@Composable
fun PlayerScreen(
    videoUri: Uri
) {

    val context = LocalContext.current

    val exoPlayer = remember(videoUri) {

        ExoPlayer.Builder(context)
            .build()
            .apply {

                setMediaItem(
                    MediaItem.fromUri(videoUri)
                )

                prepare()

                playWhenReady = true
            }
    }



    val activity = LocalContext.current as Activity

    LaunchedEffect(Unit) {
        WindowCompat.getInsetsController(
            activity.window,
            activity.window.decorView
        ).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    DisposableEffect(exoPlayer) {
        activity.requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        onDispose {
            activity.requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

            WindowCompat.getInsetsController(
                activity.window,
                activity.window.decorView

            ).show(
                WindowInsetsCompat.Type.systemBars()
            )
            exoPlayer.release()
        }
    }
    AndroidView(
        modifier = Modifier.fillMaxSize(),

        factory = { ctx ->

            PlayerView(ctx).apply {

                player = exoPlayer

                useController = true

                setShowBuffering(
                    PlayerView.SHOW_BUFFERING_WHEN_PLAYING
                )
            }
        }
    )
}