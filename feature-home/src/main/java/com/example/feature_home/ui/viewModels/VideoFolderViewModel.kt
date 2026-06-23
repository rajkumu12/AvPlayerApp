package com.example.feature_home.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_module.model.VideoItem
import com.example.core_module.usecase.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoFolderViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

    private val _videos =
        MutableStateFlow<List<VideoItem>>(emptyList())

    val videos = _videos.asStateFlow()

    fun loadVideos(
        folderPath: String
    ) {

        viewModelScope.launch {

            _videos.value =
                getVideosUseCase(folderPath)
        }
    }
}