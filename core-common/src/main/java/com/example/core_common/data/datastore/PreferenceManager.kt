package com.example.core_common.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.core_module.repository.PreferenceRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(
    name = "app_preferences"
)

class PreferenceManager @Inject constructor(
    @ApplicationContext
    private val context: Context
): PreferenceRepository {

    companion object {
        private val IS_LOGGED_IN =
            booleanPreferencesKey("is_logged_in")
    }

    suspend fun setLoggedIn(
        value: Boolean
    ) {
        Log.d("hdhdhdhddhhd", "Saving = $value")
        context.dataStore.edit {
            it[IS_LOGGED_IN] = value
        }
    }

    val isLoggedIn =
        context.dataStore.data.map {
            it[IS_LOGGED_IN] ?: false
        }

    override suspend fun saveLoginState(isLoggedIn: Boolean) {
        setLoggedIn(isLoggedIn)
    }

    override suspend fun getLoginState(): Boolean {
        return isLoggedIn.first()
    }
}