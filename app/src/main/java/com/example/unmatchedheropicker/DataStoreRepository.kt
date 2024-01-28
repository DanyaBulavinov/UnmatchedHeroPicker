package com.example.unmatchedheropicker

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

const val IMAGES_ID_DATASTORE = "images_id_datastore"
//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore()
private val Context.dataStore by preferencesDataStore(name = IMAGES_ID_DATASTORE)

class DataStoreRepository(private val context: Context) {
    val dataStore = context.dataStore
    companion object{
        val IMAGES_ID = stringSetPreferencesKey("IMAGES_ID")
    }
}