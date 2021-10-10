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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.model.dice.DiceModel
import org.leviathan941.tabletopdiceroller.model.dice.SixSidedDice

class MainViewModel : ViewModel() {
    private val _diceModels = MutableLiveData<List<DiceModel>>()
    val diceModels: LiveData<List<DiceModel>> = _diceModels

    init {
        viewModelScope.launch {
            loadTable()
        }
    }

    suspend fun dumpTable() {
        TODO("Not yet implemented")
    }

    private suspend fun loadTable() {
        // TODO: Load from database
        _diceModels.postValue(
            listOf(
                DiceModel(SixSidedDice(), 2),
                DiceModel(SixSidedDice(), 5),
                DiceModel(SixSidedDice(), 4),
            )
        )
    }
}
