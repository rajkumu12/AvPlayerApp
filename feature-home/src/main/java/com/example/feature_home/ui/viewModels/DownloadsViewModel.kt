package com.example.feature_home.ui.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_module.model.AudioFolder
import com.example.core_module.model.VideoFolder
import com.example.core_module.usecase.GetAudioFoldersUseCase
import com.example.core_module.usecase.GetVideoFoldersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadsViewModel @Inject constructor(
    private val getVideoFoldersUseCase: GetVideoFoldersUseCase,
    private val getAudioFoldersUseCase: GetAudioFoldersUseCase
) : ViewModel() {

    private val _videoFolders =
        MutableStateFlow<List<VideoFolder>>(emptyList())
    private val _audioFolders =
        MutableStateFlow<List<AudioFolder>>(emptyList())

    val videoFolders: StateFlow<List<VideoFolder>>
            = _videoFolders.asStateFlow()

    val audioFolders =
        _audioFolders.asStateFlow()

    init {
        loadVideos()
        loadAudioFolders()
    }

    private fun loadVideos() {

        viewModelScope.launch {

            _videoFolders.value =
                getVideoFoldersUseCase()
        }
    }

    private fun loadAudioFolders() {

        viewModelScope.launch {

            _audioFolders.value =
                getAudioFoldersUseCase()
        }
    }
}