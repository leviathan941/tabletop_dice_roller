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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.leviathan941.tabletopdiceroller.app.Singletons
import org.leviathan941.tabletopdiceroller.db.DIE_NO_RESULT
import org.leviathan941.tabletopdiceroller.db.entity.TableDie
import org.leviathan941.tabletopdiceroller.model.dice.DieType

class MainViewModel : ViewModel() {

    private val tableRepository = Singletons.tableRepository
    private val prefsRepository = Singletons.prefsRepository

    private val _diceState = MutableStateFlow(emptyList<TableDie>())
    val diceState: StateFlow<List<TableDie>> = _diceState

    val uiPrefs = prefsRepository.uiPreferences

    init {
        viewModelScope.launch {
            tableRepository.loadAllDice().collectLatest {
                _diceState.value = it.reversed()
            }
        }
    }

    fun addDie() {
        viewModelScope.launch {
            tableRepository.insertDie(
                TableDie(
                    die = uiPrefs.first().newDieType.toDie(),
                    result = DIE_NO_RESULT
                )
            )
        }
    }

    fun rollAll() {
        viewModelScope.launch {
            val updatedDices = diceState.value.map {
                TableDie(id = it.id, die = it.die, result = it.die.roll())
            }
            tableRepository.updateDice(updatedDices)
        }
    }

    fun roll(tableDie: TableDie) {
        setDieResult(tableDie, tableDie.die.roll())
    }

    fun removeDie(die: TableDie) {
        viewModelScope.launch {
            tableRepository.deleteDie(die)
        }
    }

    fun clear() {
        viewModelScope.launch {
            tableRepository.clear()
        }
    }

    fun changeNewDieType(type: DieType) {
        viewModelScope.launch {
            prefsRepository.updateNewDieType(type)
        }
    }

    fun setDieResult(tableDie: TableDie, result: Int) {
        viewModelScope.launch {
            with(tableDie) {
                tableRepository.updateDie(
                    TableDie(id = id, die = die, result = result)
                )
            }
        }
    }
}
