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

import org.leviathan941.tabletopdiceroller.model.dice.Die
import org.leviathan941.tabletopdiceroller.model.dice.DieSide
import org.leviathan941.tabletopdiceroller.model.dice.internal.isValuable
import org.leviathan941.tabletopdiceroller.model.dice.internal.result.SingleDieResult
import org.leviathan941.tabletopdiceroller.model.dice.internal.result.TotalDieResult
import org.leviathan941.tabletopdiceroller.model.dice.tree.Node
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult

internal class MultipleDieNode(
    private val die: Die,
) : DieResultNode {
    override val results: List<DieResult>
        get() = children.map { it.results }.flatten().filter { it.isValuable() }
            .sortedWith(MultipleDieResultComparator())

    private val _children: MutableList<DieResultNode> = mutableListOf()
    override val children: List<DieResultNode> get() = _children

    override val order: Int get() = die.type.ordinal

    override fun addValue(value: DieSide, number: Int) {
        require(isCompatibleWith(value)) {
            "Value $value is not compatible with node $this"
        }
        _children.find { it.isCompatibleWith(value) }?.addValue(value, number)
            ?: _children.add(DieResultNodeFactory.createSingleNode(value, number))
    }

    override fun isCompatibleWith(value: DieSide): Boolean =
        value.die == die

    override fun isCompatibleWith(other: Node<DieResult>): Boolean =
        other is MultipleDieNode && other.die == die

    override fun toString(): String {
        return "MultipleDieNode(dieType=${die.type}, children=$_children)"
    }
}

private class MultipleDieResultComparator : Comparator<DieResult> {
    override fun compare(o1: DieResult, o2: DieResult): Int {
        return when {
            o1 is TotalDieResult && o2 is TotalDieResult -> compareTotals(o2, o1)
            o1 is SingleDieResult && o2 is SingleDieResult -> compareSingles(o2, o1)
            o1 is TotalDieResult && o2 is SingleDieResult -> -1
            o1 is SingleDieResult && o2 is TotalDieResult -> 1
            else -> 0
        }
    }

    private fun compareTotals(o1: TotalDieResult, o2: TotalDieResult): Int {
        return if (o1.die.type == o2.die.type) {
            o1.result.compareTo(o2.result)
        } else {
            o1.die.type.ordinal.compareTo(o2.die.type.ordinal)
        }
    }

    private fun compareSingles(o1: SingleDieResult, o2: SingleDieResult): Int {
        return if (o1.dieSide.die.type == o2.dieSide.die.type) {
            o1.dieSide.sideValue.compareTo(o2.dieSide.sideValue)
        } else {
            o1.dieSide.die.type.ordinal.compareTo(o2.dieSide.die.type.ordinal)
        }
    }
}
