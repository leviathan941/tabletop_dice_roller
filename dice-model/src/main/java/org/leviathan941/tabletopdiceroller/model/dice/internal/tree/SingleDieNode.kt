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

import org.leviathan941.tabletopdiceroller.model.dice.DieSide
import org.leviathan941.tabletopdiceroller.model.dice.DieType
import org.leviathan941.tabletopdiceroller.model.dice.MunchkinDungeonDie
import org.leviathan941.tabletopdiceroller.model.dice.internal.cost
import org.leviathan941.tabletopdiceroller.model.dice.internal.result.SingleDieResult
import org.leviathan941.tabletopdiceroller.model.dice.internal.sameAs
import org.leviathan941.tabletopdiceroller.model.dice.tree.Node
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult

internal class SingleDieNode(
    val dieSide: DieSide,
    private var number: Int,
) : DieResultNode {

    override val results: List<SingleDieResult>
        get() = if (number > 0) {
            listOf(
                SingleDieResult(
                    dieSide,
                    cost = dieSide.cost,
                    result = number,
                )
            )
        } else emptyList()

    override val children: List<DieResultNode>
        get() = emptyList()

    override val order: Int get() = when (dieSide.die.type) {
        DieType.SIX_SIDED -> dieSide.sideValue
        DieType.MUNCHKIN_DUNGEON -> MunchkinDungeonDie.sideByValue(dieSide.sideValue).ordinal
    }

    override fun addValue(value: DieSide, number: Int) {
        require(isCompatibleWith(value)) {
            "Value $value is not compatible with node $this"
        }
        this.number += number
    }

    override fun isCompatibleWith(value: DieSide): Boolean =
        dieSide.sameAs(value)

    override fun isCompatibleWith(other: Node<DieResult>): Boolean =
        other is SingleDieNode && this.isCompatibleWith(other.dieSide)

    override val isExpandable: Boolean get() = false

    override fun toString(): String {
        return "SingleDieNode(dieSide=$dieSide, number=$number)"
    }
}
