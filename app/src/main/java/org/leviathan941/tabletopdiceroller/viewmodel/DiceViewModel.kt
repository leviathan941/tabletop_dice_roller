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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.app.Singletons
import org.leviathan941.tabletopdiceroller.db.entity.TableDice

class DiceViewModel(
    dice: TableDice
) : ViewModel() {

    private val tableRepository = Singletons.tableRepository

    private val _diceState = MutableStateFlow(dice)
    val diceState: StateFlow<TableDice> = _diceState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            tableRepository.loadDiceById(dice.id).collect {
                _diceState.value = it
            }
        }
    }

    fun roll() {
        viewModelScope.launch(Dispatchers.IO) {
            with(diceState.value) {
                tableRepository.updateDice(
                    TableDice(id = id, dice = dice, result = dice.roll())
                )
            }
        }
    }

    class Factory(private val dice: TableDice) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            DiceViewModel(dice) as T
    }
}
