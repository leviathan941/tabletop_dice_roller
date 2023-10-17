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

import org.leviathan941.tabletopdiceroller.model.dice.DieValue
import org.leviathan941.tabletopdiceroller.model.dice.MunchkinDungeonDie
import org.leviathan941.tabletopdiceroller.model.dice.internal.DieResultNode
import org.leviathan941.tabletopdiceroller.model.dice.internal.inTotal
import org.leviathan941.tabletopdiceroller.model.dice.internal.isLikeSword
import org.leviathan941.tabletopdiceroller.model.dice.internal.result.TotalDieResult
import org.leviathan941.tabletopdiceroller.model.dice.result.DieResult

// TODO: Make this class more generic. Maybe something like stackable die node?
internal class MunchkinSwordsDieNode : DieResultNode {
    override val results: List<DieResult>
        get() = _children.map { it.results }.flatten().sumOf { it.inTotal() }.let { total ->
            listOf(
                TotalDieResult(
                    MunchkinDungeonDie.imageBySide(MunchkinDungeonDie.Side.SWORD),
                    result = total,
                )
            )
        }

    private val _children: MutableList<DieResultNode> = mutableListOf()
    override val children: List<DieResultNode> get() = _children

    override fun isCompatibleWith(value: DieValue): Boolean =
        value.die == MunchkinDungeonDie && value.isLikeSword()

    override fun addValue(value: DieValue) {
        require(isCompatibleWith(value)) {
            "Value $value is not compatible with node $this"
        }
        _children.find { it.isCompatibleWith(value) }?.addValue(value)
            ?: _children.add(SingleDieNode(value))
    }
}
