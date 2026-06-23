package com.example.core_data.repository


import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.example.core_module.model.AudioFolder
import com.example.core_module.model.AudioItem
import com.example.core_module.model.VideoFolder
import com.example.core_module.model.VideoItem
import com.example.core_module.repository.MediaRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : MediaRepository {

    override suspend fun getVideoFolders(): List<VideoFolder> {
        Log.d("MEDIASTORE", "Query Started")
        val folderMap = mutableMapOf<String, Int>()

        val collection = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Video.Media.DATA
        )

        context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            null
        )?.use { cursor ->

            val pathColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Video.Media.DATA
                )

            while (cursor.moveToNext()) {

                val fullPath =
                    cursor.getString(pathColumn)

                val file = File(fullPath)

                val folderPath =
                    file.parent ?: continue
                Log.d("MEDIASTORE", "Video Path = $fullPath")
                folderMap[folderPath] =
                    (folderMap[folderPath] ?: 0) + 1
            }
        }

        return folderMap.map {

            VideoFolder(
                folderName = File(it.key).name,
                folderPath = it.key,
                count = it.value
            )
        }.sortedBy {
            it.folderName
        }
    }

    override suspend fun getVideos(
        folderPath: String
    ): List<VideoItem> {

        val videos = mutableListOf<VideoItem>()

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATA
        )

        context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use { cursor ->

            val idColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Video.Media._ID
                )

            val nameColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Video.Media.DISPLAY_NAME
                )

            val durationColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Video.Media.DURATION
                )

            val pathColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Video.Media.DATA
                )

            while (cursor.moveToNext()) {

                val path =
                    cursor.getString(pathColumn)

                if (!path.startsWith(folderPath))
                    continue
                val videoId = cursor.getLong(idColumn)
                videos.add(
                    VideoItem(
                        id = videoId,
                        name = cursor.getString(nameColumn),
                        duration = cursor.getLong(durationColumn),
                        contentUri = ContentUris.withAppendedId(
                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            videoId
                        )
                    )
                )
            }
        }

        return videos
    }

    override suspend fun getAudioFolders(): List<AudioFolder> {
        Log.d("iiioppp", "getAudioFolders: ")
        val folderMap = mutableMapOf<String, Int>()

        val projection = arrayOf(
            MediaStore.Audio.Media.RELATIVE_PATH
        )

        context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use { cursor ->

            val pathColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Audio.Media.RELATIVE_PATH
                )

            while (cursor.moveToNext()) {

                val relativePath =
                    cursor.getString(pathColumn)
                        ?: continue

                Log.d("iiioppp", "path: $relativePath")
                folderMap[relativePath] =
                    (folderMap[relativePath] ?: 0) + 1
            }
        }

        return folderMap.map {

            AudioFolder(
                folderName = it.key
                    .trimEnd('/')
                    .substringAfterLast('/'),
                folderPath = it.key,
                count = it.value
            )
        }.sortedBy {
            it.folderName
        }
    }


    override suspend fun getAudios(
        folderPath: String
    ): List<AudioItem> {

        val audios = mutableListOf<AudioItem>()

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.RELATIVE_PATH
        )

        context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use { cursor ->

            val idColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Audio.Media._ID
                )

            val nameColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Audio.Media.DISPLAY_NAME
                )

            val durationColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Audio.Media.DURATION
                )

            val pathColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Audio.Media.RELATIVE_PATH
                )

            while (cursor.moveToNext()) {

                val relativePath =
                    cursor.getString(pathColumn)
                        ?: continue

                if (relativePath != folderPath)
                    continue

                val audioId =
                    cursor.getLong(idColumn)

                audios.add(
                    AudioItem(
                        id = audioId,
                        name = cursor.getString(nameColumn),
                        duration = cursor.getLong(durationColumn),
                        contentUri = ContentUris.withAppendedId(
                            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                            audioId
                        )
                    )
                )
            }
        }

        return audios.sortedBy {
            it.name.lowercase()
        }
    }
}