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

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.model.dice.SixSidedDice

class MainViewModel : ViewModel() {
    var diceModels = mutableStateListOf<DiceViewModel>()
        private set

    init {
        viewModelScope.launch {
            loadTable()
        }
    }

    fun addDefaultDice() {
        diceModels.add(DiceViewModel(SixSidedDice()))
    }

    fun rollAll() {
        diceModels.forEach { it.roll() }
    }

    fun removeDice(diceModel: DiceViewModel) {
        diceModels.remove(diceModel)
    }

    suspend fun dumpTable() {
        TODO("Not yet implemented")
    }

    private suspend fun loadTable() {
        // TODO: Load from database
        diceModels.addAll(
            listOf(
                DiceViewModel(SixSidedDice(), 2),
                DiceViewModel(SixSidedDice(), 5),
                DiceViewModel(SixSidedDice(), 4),
            )
        )
    }
}
