package com.example.core_module.usecase
import com.example.core_module.repository.PreferenceRepository
import javax.inject.Inject

class GetLoginStateUseCase @Inject constructor(
    private val repository: PreferenceRepository
) {

    suspend operator fun invoke(): Boolean {
        return repository.getLoginState()
    }
}