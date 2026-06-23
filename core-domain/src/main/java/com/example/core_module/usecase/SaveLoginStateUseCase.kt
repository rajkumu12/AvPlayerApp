package com.example.core_module.usecase
import com.example.core_module.repository.PreferenceRepository
import javax.inject.Inject

class SaveLoginStateUseCase @Inject constructor(
    private val repository: PreferenceRepository
) {

    suspend operator fun invoke() {
        repository.saveLoginState(true)
    }
}