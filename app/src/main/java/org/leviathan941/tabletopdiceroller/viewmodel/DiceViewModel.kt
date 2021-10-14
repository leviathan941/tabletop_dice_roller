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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.leviathan941.tabletopdiceroller.model.dice.*
import org.leviathan941.tabletopdiceroller.model.parcel.DiceState

class DiceViewModel(
    val dice: Dice,
    init_result: Int = NO_RESULT
) : ViewModel() {
    var sideResult by mutableStateOf(toSideResult(init_result))
        private set
    val savableState: DiceState
        get() = DiceState(type = dice.type, result = sideResult.result)

    constructor(state: DiceState) :
            this(DiceFactory.create(state.type), state.result)

    init {
        require(dice.range.contains(init_result) || init_result == NO_RESULT) {
            "Invalid initial value $init_result for ${dice::class.simpleName}:[${dice.range}]"
        }
    }

    fun roll() {
        sideResult = toSideResult(dice.roll())
    }

    private fun toSideResult(result: Int) =
        if (result == NO_RESULT) {
            DiceResult(dice.previewImage(), result)
        } else {
            DiceResult(dice.sideImage(result), result)
        }

    companion object {
        const val NO_RESULT = -1
    }
}
