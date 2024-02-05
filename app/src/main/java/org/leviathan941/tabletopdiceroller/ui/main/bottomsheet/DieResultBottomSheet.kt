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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.leviathan941.tabletopdiceroller.model.dice.tree.expandable.DieExpandableTree
import org.leviathan941.tabletopdiceroller.model.dice.tree.expandable.ExpandableNode
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult
import org.leviathan941.tabletopdiceroller.viewmodel.MainViewModel

@Composable
fun DieResultBottomSheet(mainViewModel: MainViewModel) {
    val resultTree by mainViewModel.resultModel.resultTree.collectAsState(
        initial = DieExpandableTree.Empty
    )
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxHeight(0.7f),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            DieResultRows(
                nodes = resultTree.root.children,
            )
        }
    }
}

@Composable
private fun ColumnScope.DieResultRows(
    nodes: List<ExpandableNode<DieResult>>,
    depthLevel: Int = 0,
) {
    nodes.forEach { node ->
        val isExpanded by node.isExpanded.collectAsState(initial = node.isExpanded.value)
        DieResultRow(
            results = node.results,
            depthLevel = depthLevel,
            isExpandable = node.isExpandable,
            isExpanded = isExpanded,
            onExpand = { node.isExpanded.value = node.isExpanded.value.not() },
        )
        if (isExpanded) {
            DieResultRows(
                nodes = node.children,
                depthLevel = depthLevel + 1
            )
        }
    }
}
