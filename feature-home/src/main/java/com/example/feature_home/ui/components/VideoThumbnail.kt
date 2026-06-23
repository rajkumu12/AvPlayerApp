package com.example.feature_home.ui.components

import android.net.Uri
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Size
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun VideoThumbnail(
    contentUri: Uri,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    var bitmap by remember(contentUri) {
        mutableStateOf<android.graphics.Bitmap?>(null)
    }

    LaunchedEffect(contentUri) {

        bitmap = withContext(Dispatchers.IO) {

            try {

                context.contentResolver.loadThumbnail(
                    contentUri,
                    Size(320, 180),
                    null
                )

            } catch (e: Exception) {
                null
            }
        }
    }

    bitmap?.let {

        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop
        )

    } ?: Box(
        modifier = modifier
            .background(Color.DarkGray)
    )
}