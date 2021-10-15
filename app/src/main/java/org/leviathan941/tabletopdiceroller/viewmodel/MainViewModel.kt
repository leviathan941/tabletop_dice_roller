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

package org.leviathan941.tabletopdiceroller.viewmodel

import android.os.Bundle
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.model.dice.DiceFactory
import org.leviathan941.tabletopdiceroller.model.dice.defaultDice
import org.leviathan941.tabletopdiceroller.model.parcel.Table

private const val TABLE_SAVED_STATE_BUNDLE_KEY = "main_view_table_state_bundle"
private const val TABLE_STATE_KEY = "main_view_table"

class MainViewModel(savedState: SavedStateHandle) : ViewModel() {
    var diceModels = mutableStateListOf<DiceViewModel>()
        private set

    init {
        savedState.setSavedStateProvider(TABLE_SAVED_STATE_BUNDLE_KEY) {
            Bundle().apply {
                putParcelable(TABLE_STATE_KEY, Table(diceModels.map { it.savableState }))
            }
        }

        val savedModels = savedState.get<Bundle>(TABLE_SAVED_STATE_BUNDLE_KEY)
            ?.getParcelable<Table>(TABLE_STATE_KEY)?.dices?.map { DiceViewModel(it) }
        if (savedModels != null) {
            diceModels.apply {
                clear()
                addAll(savedModels)
            }
        } else {
            viewModelScope.launch { loadTable() }
        }
    }

    fun addDice() {
        val newDice = diceModels.lastOrNull()?.dice?.type?.let {
            DiceFactory.create(it)
        } ?: defaultDice()
        diceModels.add(DiceViewModel(dice = newDice))
    }

    fun roll() {
        diceModels.forEach { it.roll() }
    }

    fun removeDice(diceModel: DiceViewModel) {
        diceModels.remove(diceModel)
    }

    fun clear() {
        diceModels.clear()
    }

    suspend fun dumpTable() {
        TODO("Not yet implemented")
    }

    private suspend fun loadTable() {
        // TODO: Load from database
    }
}
