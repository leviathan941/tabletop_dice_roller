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

package org.leviathan941.tabletopdiceroller.model.dice.internal

import org.leviathan941.tabletopdiceroller.model.dice.Die
import org.leviathan941.tabletopdiceroller.model.dice.DieValue
import org.leviathan941.tabletopdiceroller.model.dice.MunchkinDungeonDie
import org.leviathan941.tabletopdiceroller.model.dice.internal.result.SingleDieResult
import org.leviathan941.tabletopdiceroller.model.dice.internal.tree.NodeContainer
import org.leviathan941.tabletopdiceroller.model.dice.result.DieResult

internal fun Die.genericSameValues(that: Int, other: Int): Boolean {
    require(that in range && other in range) { "Values must be in range $range" }
    return that == other
}

internal fun DieValue.sameAs(that: DieValue): Boolean {
    return die == that.die && die.sameValues(value, that.value)
}

internal fun DieResult.inTotal(): Int = when (this) {
        is SingleDieResult -> result * cost
        else -> result
}

internal fun DieValue.isLikeSword(): Boolean =
    MunchkinDungeonDie.sideByValue(value).let {
        it == MunchkinDungeonDie.Side.SWORD || it == MunchkinDungeonDie.Side.DOUBLE_SWORDS
    }

internal typealias DieResultNode = NodeContainer<DieResult, DieValue>
