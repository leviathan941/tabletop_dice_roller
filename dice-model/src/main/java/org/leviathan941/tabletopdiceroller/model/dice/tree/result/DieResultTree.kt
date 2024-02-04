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

package org.leviathan941.tabletopdiceroller.model.dice.tree.result

import org.leviathan941.tabletopdiceroller.model.dice.DieSide
import org.leviathan941.tabletopdiceroller.model.dice.internal.tree.DieResultNode
import org.leviathan941.tabletopdiceroller.model.dice.internal.tree.DieResultNodeFactory
import org.leviathan941.tabletopdiceroller.model.dice.tree.Node
import org.leviathan941.tabletopdiceroller.model.dice.tree.Root

class DieResultTree {
    private val _root: MutableList<DieResultNode> = mutableListOf()
    val root: Root<Node<DieResult>> get() = DieResultRoot(_root)

    fun addValue(dieSide: DieSide) {
        add(dieSide, number = 1)
    }

    @Suppress("SameParameterValue")
    private fun add(dieSide: DieSide, number: Int) {
        _root.find { it.isCompatibleWith(dieSide) }?.addValue(dieSide, number)
            ?: _root.add(DieResultNodeFactory.createTotalNode(dieSide, number))
    }

    private class DieResultRoot(
        override val children: List<DieResultNode>
    ) : Root<DieResultNode>
}
