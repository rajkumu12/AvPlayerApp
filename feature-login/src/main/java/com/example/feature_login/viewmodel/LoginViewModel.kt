package com.example.feature_login.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_common.data.datastore.PreferenceManager
import com.example.core_module.usecase.SaveLoginStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveLoginStateUseCase: SaveLoginStateUseCase
) : ViewModel() {

    fun onLoginSuccess() {
        Log.d("loginclicked ", "onLoginSuccess: ")

        viewModelScope.launch {

            saveLoginStateUseCase()
        }
    }
}