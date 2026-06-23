package com.example.core_module.usecase

import com.example.core_module.repository.MediaRepository
import javax.inject.Inject


class GetAudioFoldersUseCase @Inject constructor(
    private val mediaRepository: MediaRepository
) {

    suspend operator fun invoke() =
        mediaRepository.getAudioFolders()
}