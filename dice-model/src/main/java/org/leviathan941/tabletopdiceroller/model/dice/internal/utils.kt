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
import org.leviathan941.tabletopdiceroller.model.dice.DieSide
import org.leviathan941.tabletopdiceroller.model.dice.DieType
import org.leviathan941.tabletopdiceroller.model.dice.MunchkinDungeonDie
import org.leviathan941.tabletopdiceroller.model.dice.internal.result.SingleDieResult
import org.leviathan941.tabletopdiceroller.model.dice.internal.tree.DieResultNode
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult
import org.leviathan941.tabletopdiceroller.utils.NO_RESULT

internal fun Die.genericSameValues(that: Int, other: Int): Boolean {
    require(that in range && other in range) {
        "Values $that and $other must be in range $range"
    }
    return that == other
}

internal fun DieSide.sameAs(that: DieSide): Boolean {
    return die == that.die && die.sameValues(sideValue, that.sideValue)
}

internal fun DieResult.inTotal(): Int = when (this) {
        is SingleDieResult -> if (isValuable()) {
            result * cost
        } else {
            NO_RESULT
        }
        else -> result
}

internal fun DieSide.isLikeSword(): Boolean {
    return (die.type == DieType.MUNCHKIN_DUNGEON) &&
            MunchkinDungeonDie.sideByValue(sideValue).let {
                it == MunchkinDungeonDie.Side.SWORD || it == MunchkinDungeonDie.Side.DOUBLE_SWORDS
            }
}

internal fun DieResult.isValuable(): Boolean = when(this) {
    is SingleDieResult -> cost > 0 && result != NO_RESULT
    else -> result != NO_RESULT
}

internal fun DieResultNode.isValuable(): Boolean =
    results.any { it.isValuable() }

internal val DieSide.cost: Int get() = when (die.type) {
    DieType.SIX_SIDED -> sideValue + 1
    DieType.MUNCHKIN_DUNGEON -> when (MunchkinDungeonDie.sideByValue(sideValue)) {
        MunchkinDungeonDie.Side.DOUBLE_SWORDS -> 2
        MunchkinDungeonDie.Side.EMPTY -> 0
        else -> 1
    }
}
