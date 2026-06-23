package com.example.core_module.model

import android.net.Uri

data class AudioItem(
    val id: Long,
    val name: String,
    val duration: Long,
    val contentUri: Uri
)