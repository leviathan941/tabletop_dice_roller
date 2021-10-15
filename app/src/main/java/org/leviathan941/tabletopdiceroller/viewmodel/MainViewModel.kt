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
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.app.Singletons
import org.leviathan941.tabletopdiceroller.db.DICE_NO_RESULT
import org.leviathan941.tabletopdiceroller.db.entity.TableDice
import org.leviathan941.tabletopdiceroller.model.dice.defaultDice

class MainViewModel : ViewModel() {

    private val tableRepository = Singletons.tableRepository

    private val _dicesState = MutableStateFlow(emptyList<TableDice>())
    val dicesState: StateFlow<List<TableDice>> = _dicesState

    init {
        viewModelScope.launch {
            tableRepository.loadAllDices().collect {
                _dicesState.value = it
            }
        }
    }

    fun addDice() {
        val newDice = dicesState.value.lastOrNull()?.dice ?: defaultDice()
        viewModelScope.launch {
            tableRepository.insertDice(
                TableDice(dice = newDice, result = DICE_NO_RESULT)
            )
        }
    }

    fun roll() {
        viewModelScope.launch {
            val updatedDices = dicesState.value.map {
                TableDice(id = it.id, dice = it.dice, result = it.dice.roll())
            }
            tableRepository.updateDices(updatedDices)
        }
    }

    fun removeDice(dice: TableDice) {
        viewModelScope.launch {
            tableRepository.deleteDice(dice)
        }
    }

    fun clear() {
        viewModelScope.launch {
            tableRepository.clear()
        }
    }
}
