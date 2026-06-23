package com.example.feature_home.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_module.model.AudioItem
import com.example.core_module.model.VideoItem
import com.example.core_module.usecase.GetAudiosUseCase
import com.example.core_module.usecase.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioFolderViewModel @Inject constructor(
    private val  getAudiosUseCase: GetAudiosUseCase
) : ViewModel() {

    private val _audios =
        MutableStateFlow<List<AudioItem>>(emptyList())

    val audios = _audios.asStateFlow()

    fun loadAudios(
        folderPath: String
    ) {

        viewModelScope.launch {

            _audios.value =
                getAudiosUseCase(folderPath)
        }
    }
}