package com.example.feature_home.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.core_module.model.VideoItem
import com.example.core_ui.TextFieldBg
import com.example.core_ui.TextGray
import com.example.core_ui.TextWhite
import okhttp3.internal.concurrent.formatDuration

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun VideoItemCard(
    video: VideoItem,
    onClick: () -> Unit
) {

    fun formatDuration(durationMs: Long): String {

        val totalSeconds = durationMs / 1000

        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return if (hours > 0) {
            "%d:%02d:%02d".format(
                hours,
                minutes,
                seconds
            )
        } else {
            "%02d:%02d".format(
                minutes,
                seconds
            )
        }
    }

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = TextFieldBg
        ),
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            VideoThumbnail(
                contentUri = video.contentUri,
                modifier = Modifier
                    .width(110.dp)
                    .height(70.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = video.name,
                    color = TextWhite,
                    maxLines = 2
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = formatDuration(video.duration),
                    color = TextGray
                )
            }
        }
    }
}