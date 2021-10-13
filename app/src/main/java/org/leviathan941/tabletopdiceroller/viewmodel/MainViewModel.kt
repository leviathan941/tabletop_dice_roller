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

private const val TABLE_SAVED_STATE_KEY = "main_view_table"
private const val DICE_MODELS_SAVED_STATE_KEY = "main_view_table_dice_models"

class MainViewModel(savedState: SavedStateHandle) : ViewModel() {
    var rowModels = mutableStateListOf<DiceRowViewModel>()
        private set

    init {
        savedState.setSavedStateProvider(TABLE_SAVED_STATE_KEY) {
            SaveUtils.toBundle(DICE_MODELS_SAVED_STATE_KEY, rowModels)
        }

        val savedModels = savedState.get<Bundle>(TABLE_SAVED_STATE_KEY)?.let {
            SaveUtils.fromBundle(DICE_MODELS_SAVED_STATE_KEY, it, this::removeRow)
        }
        if (savedModels != null) {
            rowModels.apply {
                clear()
                addAll(savedModels)
            }
        } else {
            viewModelScope.launch { loadTable() }
        }
    }

    fun addDiceRow() {
        rowModels.add(newRow())
    }

    fun roll() {
        rowModels.forEach { it.roll() }
    }

    private fun newRow() = DiceRowViewModel { removeRow(it) }

    private fun removeRow(rowModel: DiceRowViewModel) {
        rowModels.remove(rowModel)
    }

    suspend fun dumpTable() {
        TODO("Not yet implemented")
    }

    private suspend fun loadTable() {
        // TODO: Load from database
        rowModels.add(DiceRowViewModel(onLastDiceRemoved = this::removeRow))
    }
}
