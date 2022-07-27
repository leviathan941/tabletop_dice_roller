/*
 * Tabletop Dice Roller
 * Copyright (C) 2021 Alexey Kuzin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.leviathan941.tabletopdiceroller.app

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.leviathan941.tabletopdiceroller.app.preferences.CommonPreferencesRepository
import org.leviathan941.tabletopdiceroller.db.AppDatabase

object Singletons {
    private const val COMMON_PREFS_NAME = "common_preferences"

    private lateinit var database: AppDatabase

    private val Context.commonDataStore: DataStore<Preferences> by
            preferencesDataStore(name = COMMON_PREFS_NAME)

    val tableRepository get() = database.tableRepository

    lateinit var prefsRepository: CommonPreferencesRepository

    fun init(context: Context) {
        database = AppDatabase.create(context)
        prefsRepository = CommonPreferencesRepository(context.commonDataStore)
    }
}
