/*
 * Tabletop Dice Roller
 * Copyright (C) 2023 Alexey Kuzin
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

package org.leviathan941.tabletopdiceroller.model.dice.internal.tree

import org.leviathan941.tabletopdiceroller.model.dice.DieType
import org.leviathan941.tabletopdiceroller.model.dice.DieValue
import org.leviathan941.tabletopdiceroller.model.dice.internal.DieResultNode
import org.leviathan941.tabletopdiceroller.model.dice.internal.isLikeSword
import org.leviathan941.tabletopdiceroller.model.dice.internal.isNoResult

internal object DieResultNodeFactory {

    fun createTotalNode(value: DieValue): DieResultNode = when (value.die.type) {
        DieType.SIX_SIDED -> TotalDieNode(value.die)
        DieType.MUNCHKIN_DUNGEON -> MultipleDieNode(value.die)
    }.apply {
        addValue(value)
    }

    fun createSingleNode(value: DieValue): DieResultNode = when (value.die.type) {
        DieType.SIX_SIDED -> SingleDieNode(value)
        DieType.MUNCHKIN_DUNGEON -> if (value.isLikeSword()) {
            MunchkinSwordsDieNode().apply {
                addValue(value)
            }
        } else {
            SingleDieNode(value)
        }
    }
}
