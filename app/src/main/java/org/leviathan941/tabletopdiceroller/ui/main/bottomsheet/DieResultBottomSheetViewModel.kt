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
import kotlinx.coroutines.flow.map
import org.leviathan941.tabletopdiceroller.db.DIE_NO_RESULT
import org.leviathan941.tabletopdiceroller.db.entity.TableDie
import org.leviathan941.tabletopdiceroller.model.dice.DieValue
import org.leviathan941.tabletopdiceroller.model.dice.result.DieResultTree
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

internal fun MainViewModel.dieResultTree(): Flow<DieResultTree> = diceState.map { dieStates ->
    DieResultTree().apply {
        dieStates.forEach { dieState ->
            addValue(dieState.toDieValue())
        }
    }
}

private fun TableDie.toDieValue(): DieValue = DieValue(die, value)
