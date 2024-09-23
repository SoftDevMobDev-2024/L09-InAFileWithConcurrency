package au.edu.swin.sdmd.l08_inafile.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KeyStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("savedOption")
        val OPTION_KEY = intPreferencesKey("last_option")
    }
    val getOption: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[OPTION_KEY] ?: 0
    }
    suspend fun saveOption(option: Int) {
        context.dataStore.edit { preferences ->
            preferences[OPTION_KEY] = option
        }
    }
}