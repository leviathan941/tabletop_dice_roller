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

package org.leviathan941.tabletopdiceroller.model.dice.result

import org.leviathan941.tabletopdiceroller.model.dice.DieValue
import org.leviathan941.tabletopdiceroller.model.dice.internal.tree.DieResultNodeFactory
import org.leviathan941.tabletopdiceroller.model.dice.internal.tree.NodeContainer
import org.leviathan941.tabletopdiceroller.model.dice.tree.Node

class DieResultTree {
    private val _root: MutableList<NodeContainer<DieResult, DieValue>> = mutableListOf()
    val root: List<Node<DieResult>> get() = _root

    fun addValue(value: DieValue) {
        _root.find { it.isCompatibleWith(value) }?.addValue(value)
            ?: _root.add(DieResultNodeFactory.createTotalNode(value))
    }
}
