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
import org.leviathan941.tabletopdiceroller.model.dice.MunchkinDungeonDie
import org.leviathan941.tabletopdiceroller.model.dice.internal.inTotal
import org.leviathan941.tabletopdiceroller.model.dice.internal.isLikeSword
import org.leviathan941.tabletopdiceroller.model.dice.internal.isValuable
import org.leviathan941.tabletopdiceroller.model.dice.internal.result.TotalDieResult
import org.leviathan941.tabletopdiceroller.model.dice.tree.Node
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult

// TODO: Make this class more generic. Maybe something like stackable die node?
internal class MunchkinSwordsDieNode : DieResultNode {
    override val results: List<DieResult>
        get() = _children.map { it.results }.flatten().sumOf { it.inTotal() }.let { total ->
            total.takeIf { it > 0 }?.let {
                listOf(
                    TotalDieResult(
                        MunchkinDungeonDie.imageBySide(MunchkinDungeonDie.Side.SWORD),
                        result = total,
                    )
                )
            } ?: emptyList()
        }

    private val _children: MutableList<DieResultNode> = mutableListOf()
    override val children: List<DieResultNode> get() = _children

    override fun isCompatibleWith(value: DieSide): Boolean =
        value.die == MunchkinDungeonDie && value.isLikeSword()

    override fun addValue(value: DieSide, number: Int) {
        require(isCompatibleWith(value)) {
            "Value $value is not compatible with node $this"
        }
        _children.find { it.isCompatibleWith(value) }?.addValue(value, number)
            ?: _children.add(SingleDieNode(value, number))
    }

    override fun isCompatibleWith(other: Node<DieResult>): Boolean =
        other is MunchkinSwordsDieNode

    override val isExpandable: Boolean get() = _children.any { it.isValuable() }

    override fun toString(): String {
        return "MunchkinSwordsDieNode(children=$_children)"
    }
}
