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
import org.leviathan941.tabletopdiceroller.model.dice.internal.inTotal
import org.leviathan941.tabletopdiceroller.model.dice.internal.isValuable
import org.leviathan941.tabletopdiceroller.model.dice.internal.result.TotalDieResult
import org.leviathan941.tabletopdiceroller.model.dice.tree.Node
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult

internal class TotalDieNode(
    private val die: Die,
) : DieResultNode {
    override val results: List<DieResult>
        get() = children.map { it.results }.flatten()
            .sumOf { it.inTotal() }
            .takeIf { it > 0 }?.let { totalResult ->
                listOf(
                    TotalDieResult(
                        die.previewImage,
                        result = totalResult,
                    )
                )
            } ?: emptyList()

    private val _children = mutableListOf<DieResultNode>()
    override val children: List<DieResultNode> get() = _children

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
        other is TotalDieNode && other.die == die

    override val isExpandable: Boolean get() = _children.any { it.isExpandable || it.isValuable() }

    override fun toString(): String {
        return "TotalDieNode(dieType=${die.type}, children=$_children)"
    }
}
