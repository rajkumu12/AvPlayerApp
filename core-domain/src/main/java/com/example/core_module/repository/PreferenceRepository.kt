package com.example.core_module.repository

interface PreferenceRepository {

    suspend fun saveLoginState(isLoggedIn: Boolean)

    suspend fun getLoginState(): Boolean
}