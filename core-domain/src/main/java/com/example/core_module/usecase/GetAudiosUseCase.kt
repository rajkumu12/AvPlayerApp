package com.example.core_module.usecase

import com.example.core_module.repository.MediaRepository
import javax.inject.Inject

class GetAudiosUseCase @Inject constructor(
    private val mediaRepository: MediaRepository
) {

    suspend operator fun invoke(
        folderPath: String
    ) = mediaRepository.getAudios(folderPath)
}