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
import org.leviathan941.tabletopdiceroller.model.dice.DieValue
import org.leviathan941.tabletopdiceroller.model.dice.internal.DieResultNode
import org.leviathan941.tabletopdiceroller.model.dice.result.DieResult

internal class MultipleDieNode(
    private val die: Die,
) : DieResultNode {
    override val results: List<DieResult>
        get() = children.map { it.results }.flatten()

    private val _children: MutableList<DieResultNode> = mutableListOf()
    override val children: List<DieResultNode> get() = _children

    override fun addValue(value: DieValue) {
        require(isCompatibleWith(value)) {
            "Value $value is not compatible with node $this"
        }
        _children.find { it.isCompatibleWith(value) }?.addValue(value)
            ?: _children.add(DieResultNodeFactory.createSingleNode(value))
    }

    override fun isCompatibleWith(value: DieValue): Boolean =
        value.die == die
}
