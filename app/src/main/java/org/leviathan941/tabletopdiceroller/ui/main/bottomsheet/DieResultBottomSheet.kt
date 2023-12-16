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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.model.dice.result.DieResultTree
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

@Composable
fun ColumnScope.DieResultBottomSheet(mainViewModel: MainViewModel) {
    val resultTree by mainViewModel.dieResultTree().collectAsState(initial = DieResultTree())

    Column(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(bottom = 15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        resultTree.root.forEach { node ->
            DieResultRow(
                results = node.results,
            )
        }
    }
}
