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

package org.leviathan941.tabletopdiceroller.app.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.leviathan941.tabletopdiceroller.model.dice.DieType

class CommonPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
) {
    val uiPreferences: Flow<UiPreferences> =
        dataStore.data.map { prefs ->
            val newDieType = prefs[PreferencesKeys.NEW_DIE_TYPE] ?: DieType.SIX_SIDED.name
            UiPreferences(enumValueOf(newDieType))
        }

    suspend fun updateNewDieType(type: DieType) {
        dataStore.edit { prefs ->
            prefs[PreferencesKeys.NEW_DIE_TYPE] = type.name
        }
    }
}
