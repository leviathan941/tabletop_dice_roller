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

package org.leviathan941.tabletopdiceroller.model.dice

class DiceModel(
    val dice: Dice,
    init_result: Int = NO_RESULT
) {
    init {
        require(dice.range.contains(init_result)) {
            "Invalid initial value $init_result for ${dice::class.simpleName}:[${dice.range}]"
        }
    }

    var result: Int = init_result
        private set

    fun roll(): Int = dice.roll().also {
        result = it
    }

    companion object {
        const val NO_RESULT = -1
    }
}
