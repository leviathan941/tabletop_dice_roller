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

package org.leviathan941.tabletopdiceroller.model.dice.tree.expandable

import kotlinx.coroutines.flow.MutableStateFlow
import org.leviathan941.tabletopdiceroller.model.dice.tree.Expandable
import org.leviathan941.tabletopdiceroller.model.dice.tree.Node

interface ExpandableNode<Result> : Expandable, Node<Result> {
    val isExpanded: MutableStateFlow<Boolean>

    override val children: List<ExpandableNode<Result>>
}
