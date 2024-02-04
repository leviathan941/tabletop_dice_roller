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

package org.leviathan941.tabletopdiceroller.ui.main.bottomsheet

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.leviathan941.tabletopdiceroller.db.entity.TableDie
import org.leviathan941.tabletopdiceroller.model.dice.DieSide
import org.leviathan941.tabletopdiceroller.model.dice.tree.expandable.DieExpandableTree
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResultTree
import org.leviathan941.tabletopdiceroller.utils.NO_RESULT

class DieResultBottomSheetModel {
    private val _resultTreeState = MutableStateFlow(DieExpandableTree.Empty)
    val resultTree: Flow<DieExpandableTree>
        get() = _resultTreeState.asStateFlow()

    fun setResultsTree(dice: List<TableDie>) {
        _resultTreeState.value = DieExpandableTree(
            resultTree = dice.toDieResultTree(),
            previouslyExpandedNodes = _resultTreeState.value.expandedNodes(),
        )
    }
}

internal fun List<TableDie>.toDieResultTree(): DieResultTree = DieResultTree().apply {
    forEach { dieState ->
        addValue(dieState.toDieSide() ?: return@forEach)
    }
}

private fun TableDie.toDieSide(): DieSide? =
    if (value != NO_RESULT) DieSide(die, value) else null
