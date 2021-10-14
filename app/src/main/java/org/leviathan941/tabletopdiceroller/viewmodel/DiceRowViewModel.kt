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
import org.leviathan941.tabletopdiceroller.model.dice.DiceFactory
import org.leviathan941.tabletopdiceroller.model.dice.defaultDice
import org.leviathan941.tabletopdiceroller.model.parcel.DiceState

class DiceRowViewModel(
    private val onLastDiceRemoved: (DiceRowViewModel) -> Unit
) : ViewModel() {
    var diceModels = mutableStateListOf<DiceViewModel>()
        private set
    val diceStates: List<DiceState>
        get() = diceModels.map { it.savableState }

    constructor(
        states: List<DiceState>,
        onLastDiceRemoved: (DiceRowViewModel) -> Unit,
    ) : this(onLastDiceRemoved) {
        diceModels.addAll(states.map { DiceViewModel(it) })
    }

    fun roll() {
        diceModels.forEach { it.roll() }
    }

    fun removeDice(diceModel: DiceViewModel) {
        with(diceModels) {
            remove(diceModel)
            if (isEmpty()) {
                onLastDiceRemoved(this@DiceRowViewModel)
            }
        }
    }

    fun addDice() {
        val newDice = diceModels.lastOrNull()?.dice?.type?.let {
            DiceFactory.create(it)
        } ?: defaultDice()
        diceModels.add(DiceViewModel(dice = newDice))
    }
}
